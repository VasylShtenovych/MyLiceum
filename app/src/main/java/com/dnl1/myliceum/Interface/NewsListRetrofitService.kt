package com.dnl1.myliceum.Interface

import com.dnl1.myliceum.Model.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsListRetrofitService {
    @GET("news")
    fun getNewsList(): Call<MutableList<News>>
}