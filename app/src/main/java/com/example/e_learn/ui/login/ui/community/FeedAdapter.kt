package com.example.e_learn.ui.login.ui.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learn.R
import com.example.e_learn.data.model.FeedModel
import com.example.e_learn.data.model.FeedResponse


class FeedAdapter (): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>()  {

    var feeds:List<FeedModel>? = null

    fun setData(newData: FeedResponse){
        feeds = newData.feeds
        notifyDataSetChanged()
    }
        inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardview: CardView = itemView.findViewById(R.id.cardView)
             val titles:TextView = itemView.findViewById(R.id.title)
             val quest:TextView = itemView.findViewById(R.id.question)
             val userName:TextView = itemView.findViewById(R.id.user)

        init {
            cardview.setOnClickListener{
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                val feed = feeds?.get(position)
                if (feed != null) {
                    Toast.makeText(itemView.context,"Clicked ${feed.title}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tile, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return feeds?.size ?: 0

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
       val tiledata = feeds?.get(position)
        if (tiledata != null) {
            holder.titles.text = tiledata.title
        }
        if (tiledata != null) {
            holder.quest.text = tiledata.question
        }
        if (tiledata != null) {
            holder.userName.text = tiledata.username
        }
    }

//    fun bind(data:feeds){
//        titles.text = data.title
//        quest.text = .question
//    }




}