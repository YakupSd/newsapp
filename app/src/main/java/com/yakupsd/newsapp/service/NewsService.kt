package com.yakupsd.newsapp.service

import com.yakupsd.newsapp.model.GetNewsResponse
import com.yakupsd.newsapp.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getData(q: String?): Call<GetNewsResponse> {
        return api.getNews(q)
    }

    fun getBreakingNews(): Call<GetNewsResponse> {
        return api.getBreakingNews()
    }

}