package com.dnl1.myliceum.Interface

import com.dnl1.myliceum.Model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsDetailRetrofitService {
    @GET("news/{Id}")
    fun search(@Path("Id") id: String) : Call<News>
}