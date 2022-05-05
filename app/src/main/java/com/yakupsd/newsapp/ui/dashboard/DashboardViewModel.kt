package com.yakupsd.newsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupsd.newsapp.database.NewsDatabase
import com.yakupsd.newsapp.model.NewsDto
import kotlinx.coroutines.launch

class DashboardViewModel(private val database: NewsDatabase) : ViewModel() {

    private val _favoritesArticles = MutableLiveData<List<NewsDto>>()
    val favoritesArticles: LiveData<List<NewsDto>> = _favoritesArticles


    fun getNews() {
        viewModelScope.launch {
          _favoritesArticles.value =   database.getArticleDao().getAllArticles()
        }
    }

    fun deleteNews(newsDto: NewsDto) {
        viewModelScope.launch {
            database.getArticleDao().deleteArticle(newsDto)
            getNews()
        }
    }
}