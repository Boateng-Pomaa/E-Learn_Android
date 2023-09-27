package com.example.e_learn.ui.login.ui.answer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learn.R
import com.example.e_learn.data.model.AnswerModel
import com.example.e_learn.data.model.AnswerResponse

class AnswerAdapter(var answer:List<AnswerModel>,private val onVoteClickListener: OnVoteClickListener):
    RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.AnswerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return AnswerViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: AnswerAdapter.AnswerViewHolder, position: Int) {
        val answerData = answer[position]
        holder.bind(answerData)
        holder.upVote.setOnClickListener {
            onVoteClickListener.onUpvoteClick(answerData._id)
        }
        holder.downVote.setOnClickListener {
            onVoteClickListener.onDownvoteClick(answerData._id)
        }
    }

    override fun getItemCount(): Int {
        return answer.size
    }
    interface OnVoteClickListener {
        fun onUpvoteClick(answerId: String)
        fun onDownvoteClick(answerId: String)
    }
    fun setAnswers(newData: AnswerResponse){
        answer = newData.answers
        notifyDataSetChanged()
    }
    inner class AnswerViewHolder(itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        private val answerTxt: TextView = itemView1.findViewById(R.id.txtAnswer)
        val upVote: ImageButton = itemView1.findViewById(R.id.imageButton2)
        val downVote:ImageButton = itemView1.findViewById(R.id.imageButton3)
        private val timeTxt:TextView = itemView1.findViewById(R.id.txtTime)
        private val count1:TextView = itemView1.findViewById(R.id.txtCount)
        private val userName: TextView = itemView1.findViewById(R.id.txtUser)

        fun bind(answer:AnswerModel){
            answerTxt.text = answer.content
            count1.text = answer.upvote.toString()
            userName.text = answer.username
            timeTxt.text = answer.createdAt

            upVote.setOnClickListener {
                answer.upvote++
                count1.text = answer.upvote.toString()
                notifyItemChanged(adapterPosition)
            }
            downVote.setOnClickListener {
                    answer.upvote--
                    count1.text = answer.upvote.toString()
                    notifyItemChanged(adapterPosition)
            }

        }
    }

}