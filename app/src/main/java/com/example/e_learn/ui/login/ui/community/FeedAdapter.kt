package com.example.e_learn.ui.login.ui.community

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learn.R
import com.example.e_learn.data.model.FeedModel
import com.example.e_learn.data.model.FeedResponse


class FeedAdapter ( var feeds:List<FeedModel>,private val listen:OnItemClickListener): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>()  {
    fun setData(newData: FeedResponse){
        feeds = newData.feeds
        notifyDataSetChanged()
    }
        inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),OnClickListener {
        private val cardview: CardView = itemView.findViewById(R.id.cardView)
             val titles:TextView = itemView.findViewById(R.id.title)
             val quest:TextView = itemView.findViewById(R.id.question)
             val userName:TextView = itemView.findViewById(R.id.user)
       init {
           cardview.setOnClickListener(this)
            }
            override fun onClick(view: View){
                val position = absoluteAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val feed = feeds[position]
                    listen.onItemClick(feed)
                }
            }
        }
    interface OnItemClickListener{
        fun onItemClick(feed: FeedModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tile, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return feeds.size

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
       val tiledata = feeds[position]
        holder.titles.text = tiledata.title
        holder.quest.text = tiledata.question
        holder.userName.text = tiledata.username
    }

    //    fun bind(data:feeds){
//        titles.text = data.title
//        quest.text = .question
//    }

}
