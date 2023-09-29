package com.example.e_learn.ui.login.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learn.R
import com.example.e_learn.data.model.GetScoreResponse
import com.example.e_learn.data.model.ScoreModel

class ScoreAdapter (private var score:List<ScoreModel>): RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreAdapter.ScoreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.score_item, parent, false)
        return ScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScoreAdapter.ScoreViewHolder, position: Int) {
        val scoreData = score[position]
        holder.bind(scoreData)
    }


    override fun getItemCount(): Int {
        return score.size

    }

    fun setScores(newData: GetScoreResponse) {
        score = newData.Score
        notifyDataSetChanged()
    }

    inner class ScoreViewHolder(itemView2: View) : RecyclerView.ViewHolder(itemView2) {
        private val quizTxt: TextView = itemView2.findViewById(R.id.txtQuiz)
        private val scoreTxt: TextView = itemView2.findViewById(R.id.txtScore)

        fun bind(scores: ScoreModel) {
            quizTxt.text = scores.quiz
            scoreTxt.text = scores.score.toString()
        }

    }
}