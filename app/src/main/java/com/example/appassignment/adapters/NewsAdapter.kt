package com.example.appassignment.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appassignment.R
import com.example.appassignment.models.NewsItemsModel
import com.squareup.picasso.Picasso
import java.net.URL


class NewsAdapter (private val mList: List<NewsItemsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    lateinit var url: String

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val published: TextView = itemView.findViewById(R.id.newsItem_uploadDate_text)
        val title: TextView = itemView.findViewById(R.id.newsItem_newsHeadline_text)
        val disc: TextView = itemView.findViewById(R.id.newsItem_details_text)
        val image: ImageView = itemView.findViewById(R.id.newsThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val newsModelItems = mList[position]

        holder.published.text = newsModelItems.published + newsModelItems.source
        holder.title.text = newsModelItems.title
        holder.disc.text = newsModelItems.description
        if (newsModelItems.imageUrl.isNotEmpty()){
            Picasso.get()
                .load(newsModelItems.imageUrl)
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}