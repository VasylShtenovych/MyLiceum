package com.dnl1.myliceum.Common


import com.dnl1.myliceum.Interface.TeachersListRetrofitService
import com.dnl1.myliceum.RetrofitClient

object TeachersListCommon {
    private val BASE_URL = "http://www.dnl1.if.ua/api/"

    val retrofitService: TeachersListRetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(TeachersListRetrofitService::class.java)
}
