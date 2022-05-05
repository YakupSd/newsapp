package com.yakupsd.newsapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yakupsd.newsapp.R
import com.yakupsd.newsapp.databinding.ItemFavoriteNewsBinding
import com.yakupsd.newsapp.model.NewsDto

class FavoritesAdapter(
    var newsList: List<NewsDto>,
    val itemclicklistener: (NewsDto) -> Unit,
val deleteClickItemClickListener:(NewsDto) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemFavoriteNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsDto, itemclicklistener: (NewsDto) -> Unit, deleteClickItemClickListener:(NewsDto) -> Unit) {
            binding.newsTitle.text = item.newsTitle
            binding.newsDescription.text = item.newsDescription
            Glide.with(binding.root.context).load(item.newsImage).into(binding.imageViewNews)

            binding.root.setOnClickListener {
                itemclicklistener(item)
            }
            binding.imgDeleteFavorite.setOnClickListener {
                deleteClickItemClickListener.invoke(item)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val binding = ItemFavoriteNewsBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_news, parent, false)
        )
        return NewsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = newsList[position]
        holder.bind(item, itemclicklistener,deleteClickItemClickListener)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}