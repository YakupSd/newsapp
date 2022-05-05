package com.yakupsd.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yakupsd.newsapp.R
import com.yakupsd.newsapp.databinding.ItemNewsBinding
import com.yakupsd.newsapp.model.News

class NewsAdapter(var newsList: ArrayList<News>, val itemclicklistener: (News) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News, itemclicklistener: (News) -> Unit) {
            binding.newsTitle.text = item.newsTitle
            binding.newsDescription.text = item.newsDescription
            Glide.with(binding.root.context).load(item.newsUrlToImage).into(binding.imageViewNews)


            binding.root.setOnClickListener {
                itemclicklistener(item)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val binding = ItemNewsBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
        return NewsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = newsList[position]
        holder.bind(item, itemclicklistener)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}