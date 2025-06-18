package com.example.viridis.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:3000/api/"

val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply{
        level= HttpLoggingInterceptor.Level.BODY
    })
    .build()

private val retrofit:Retrofit= Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


val gardenService: GardenService by lazy {
    retrofit.create(GardenService::class.java)
}

