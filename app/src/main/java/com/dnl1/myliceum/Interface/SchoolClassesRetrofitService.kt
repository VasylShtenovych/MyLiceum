package com.dnl1.myliceum.Interface

import com.dnl1.myliceum.Model.SchoolClasses
import retrofit2.Call
import retrofit2.http.GET

interface SchoolClassesRetrofitService {
    @GET("schedule")
    fun getClassesList(): Call<MutableList<SchoolClasses>>
}