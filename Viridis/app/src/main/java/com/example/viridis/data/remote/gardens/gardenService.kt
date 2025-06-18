package com.example.viridis.data.remote.gardens

import com.example.viridis.data.model.Garden
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GardenService {

    @GET("api/gardens/{userId}")
    suspend fun getGardens(
        @Path("userId") userId: String
    ): List<Garden>

    @POST("api/gardens/{userId}")
    suspend fun addGarden(
        @Path("userId") userId: String,
        @Body garden: Garden
    ): Garden

    @DELETE("api/gardens/{userId}/{gardenId}")
    suspend fun deleteGarden(
        @Path("userId") userId: String,
        @Path("gardenId") gardenId: String
    )
}
