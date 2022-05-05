package com.yakupsd.newsapp.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakupsd.newsapp.database.NewsDatabase

class NewsDetailViewModelFactory(
    private val database: NewsDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsDetailViewModel(database) as T
    }
}



