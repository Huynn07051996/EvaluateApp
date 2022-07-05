package com.example.evaluateroom.utils

import com.example.evaluateapp.api.RetrofitService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {

    //Base url of the api
    private const val BASE_URL = "https://fakestoreapi.com/"

    //create retrofit service
    fun ApiService(): RetrofitService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
}