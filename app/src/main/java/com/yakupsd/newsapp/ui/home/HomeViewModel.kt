package com.yakupsd.newsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakupsd.newsapp.model.GetNewsResponse
import com.yakupsd.newsapp.service.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val newsService = NewsService()
    val allNews = MutableLiveData<GetNewsResponse>()


    init {
        getDataFromApi(q = String())

    }

    fun getDataFromApi(q: String?) {

        val response = newsService.getData(q)
        response.enqueue(object : Callback<GetNewsResponse> {
            override fun onResponse(
                call: Call<GetNewsResponse>,
                response: Response<GetNewsResponse>
            ) {
                if (response.isSuccessful) {
                    allNews.value = response.body()
                }

            }

            override fun onFailure(call: Call<GetNewsResponse>, t: Throwable) {

            }


        })

    }

    fun getBreakingNews() {

        val response = newsService.getBreakingNews()
        response.enqueue(object : Callback<GetNewsResponse> {
            override fun onResponse(
                call: Call<GetNewsResponse>,
                response: Response<GetNewsResponse>
            ) {
                if (response.isSuccessful) {
                    allNews.value = response.body()
                }
            }

            override fun onFailure(call: Call<GetNewsResponse>, t: Throwable) {

            }

        })
    }


}