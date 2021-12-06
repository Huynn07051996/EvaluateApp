package com.example.evaluateappfinal.api

import com.example.evaluateapp.api.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val BASE_URL = "https://fakestoreapi.com/"
    val reTrofitClient: Retrofit.Builder by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

    }
    val retrofitApi: RetrofitService by lazy {
        reTrofitClient
            .build()
            .create(RetrofitService::class.java)
    }


}