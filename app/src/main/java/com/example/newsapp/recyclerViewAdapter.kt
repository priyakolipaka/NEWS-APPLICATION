package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news_data.model.Articles
import com.squareup.picasso.Picasso

class recyclerViewAdapter(private val news: ArrayList<Articles>):
    RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder>()
{
    private   lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        //        val image :ShapeableImageView = itemView.findViewById(R.id.image)
        val view: TextView = itemView.findViewById(R.id.text)
        val image: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = news[position]
        holder.view.text=currentItem.title
        val picasso = Picasso.get()
        picasso.load(currentItem.urlToImage).into(holder.image)


    }

    override fun getItemCount(): Int {
        return news.size
    }
}