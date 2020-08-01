package com.dnl1.myliceum

import com.dnl1.myliceum.Interface.NewsDetailRetrofitService

object NewsDetailCommon {
    private val BASE_URL = "http://www.dnl1.if.ua/api/"

    val retrofitService: NewsDetailRetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(NewsDetailRetrofitService::class.java)
}