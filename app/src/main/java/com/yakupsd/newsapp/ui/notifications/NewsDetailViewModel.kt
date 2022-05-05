package com.yakupsd.newsapp.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupsd.newsapp.database.NewsDatabase
import com.yakupsd.newsapp.model.News
import com.yakupsd.newsapp.model.NewsDto
import kotlinx.coroutines.launch

class NewsDetailViewModel(private val database: NewsDatabase) : ViewModel() {


    fun saveNews(news: News) {
        viewModelScope.launch {
            database.getArticleDao().insert(
                NewsDto(
                    newsDescription = news.newsDescription,
                    newsImage = news.newsUrlToImage,
                    newsTitle = news.newsTitle,
                    newsUrl = news.newsUrl,
                    sourceName = news.source.newsName,
                    publishedAt = news.newsPublishedAt,
                    author = news.newsAuthor
                )
            )
        }
    }
}