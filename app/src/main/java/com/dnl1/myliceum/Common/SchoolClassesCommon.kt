package com.dnl1.myliceum.Common

import com.dnl1.myliceum.Interface.SchoolClassesRetrofitService
import com.dnl1.myliceum.RetrofitClient

object SchoolClassesCommon {
    private val BASE_URL = "http://www.dnl1.if.ua/api/"

    val retrofitService: SchoolClassesRetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(SchoolClassesRetrofitService::class.java)
}