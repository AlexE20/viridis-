package com.pdm.viridis.data.remote.gardens

import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.remote.responses.GardenRequest
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
        @Body garden: GardenRequest
    ): Garden

    @DELETE("api/gardens/{userId}/{gardenId}")
    suspend fun deleteGarden(
        @Path("userId") userId: String,
        @Path("gardenId") gardenId: String
    )

    @GET("api/gardens/garden/{userId}")
    suspend fun getGarden(
        @Path("userId") userId: String
    ) : Garden
}
