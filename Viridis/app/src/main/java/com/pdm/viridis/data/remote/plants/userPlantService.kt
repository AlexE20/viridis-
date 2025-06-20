package com.pdm.viridis.data.remote.plants


import com.pdm.viridis.data.model.UserPlant
import retrofit2.http.GET
import retrofit2.http.Path

interface UserPlantService {

    @GET("api/userPlants/{userId}/{gardenId}")
    suspend fun getPlants(
        @Path("userId") userId: String,
        @Path("gardenId") gardenId: String
    ): List<UserPlant>






}