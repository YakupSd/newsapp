package com.yakupsd.newsapp.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakupsd.newsapp.database.NewsDatabase

class DashboardViewModelFactory(
    private val database: NewsDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(database) as T
    }
}



