package com.example.e_learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubjectsAdapter (private val subjects: List<Subject>,private val listener:onItemClickListener) :RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        // Bind the necessary views from the item layout
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View){
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                val subject = subjects[position]
                listener.onItemClick(subject)
            }
        }

    }
    interface onItemClickListener{
        fun onItemClick(subject:Subject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        // Inflate the item layout and create a new CourseViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        // Bind the data at the specified position to the views in the CourseViewHolder
        val course = subjects[position]
        holder.titleTextView.text = course.title

    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}