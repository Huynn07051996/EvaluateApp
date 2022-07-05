package com.example.evaluateapp.api

import com.example.evaluateroom.model.Evaluate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("products")
    fun getDataFormApi(): Call<List<Evaluate>>

    @GET("products")
    suspend fun getDataFormApi2(): List<Evaluate>

    @GET("products/{id}")
    fun getEvaluate(@Path("id")id :Int ): Call<Evaluate>
}