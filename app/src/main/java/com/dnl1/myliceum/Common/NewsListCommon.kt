package com.dnl1.myliceum.Common

import com.dnl1.myliceum.Interface.NewsListRetrofitService
import com.dnl1.myliceum.RetrofitClient

object NewsListCommon {
    private val BASE_URL = "http://www.dnl1.if.ua/api/"

    val retrofitService: NewsListRetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(NewsListRetrofitService::class.java)
}