package com.example.e_learn.ui.login.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learn.R
import com.example.e_learn.data.model.AnswerModel
import com.example.e_learn.data.model.AnswerResponse

class UserAnswersAdapter (var answer:List<AnswerModel>): RecyclerView.Adapter<UserAnswersAdapter.AnswersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAnswersAdapter.AnswersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return AnswersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        val answerData = answer[position]
        holder.bind(answerData)
    }


    override fun getItemCount(): Int {
        return answer.size

    }

    fun setAnswers(newData: AnswerResponse) {
        answer = newData.answers
        notifyDataSetChanged()
    }

    inner class AnswersViewHolder(itemView2: View) : RecyclerView.ViewHolder(itemView2) {
        private val answerTxt: TextView = itemView2.findViewById(R.id.txtAnswer)
        private val upVote: ImageButton = itemView2.findViewById(R.id.imageButton2)
        private val downVote: ImageButton = itemView2.findViewById(R.id.imageButton3)
        private val timeTxt: TextView = itemView2.findViewById(R.id.txtTime)
        private val count1: TextView = itemView2.findViewById(R.id.txtCount)
        private val userName: TextView = itemView2.findViewById(R.id.txtUser)


        fun bind(answer: AnswerModel) {
            answerTxt.text = answer.content
            count1.text = answer.upvote.toString()
            userName.text = answer.username
            timeTxt.text = answer.createdAt
        }

    }
}