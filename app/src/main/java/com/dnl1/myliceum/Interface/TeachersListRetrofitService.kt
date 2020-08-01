package com.dnl1.myliceum.Interface


import com.dnl1.myliceum.Model.Teacher
import retrofit2.Call
import retrofit2.http.GET

interface TeachersListRetrofitService {
    @GET("teachers")
    fun getNewsList(): Call<MutableList<Teacher>>
}