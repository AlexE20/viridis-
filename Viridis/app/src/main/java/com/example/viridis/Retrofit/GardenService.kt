package com.example.viridis.Retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface GardenService {
    @GET("gardens/{userId}")
    suspend fun getGardens(@Path("userId")userId:String): List<GardenResponse>

}