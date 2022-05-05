package com.yakupsd.newsapp.service

import com.yakupsd.newsapp.model.GetNewsResponse
import com.yakupsd.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {


    @GET("v2/everything")
    fun getNews(
        @Query("q")
        searchQuery: String?,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Call<GetNewsResponse>

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String = "tr",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Call<GetNewsResponse>


}