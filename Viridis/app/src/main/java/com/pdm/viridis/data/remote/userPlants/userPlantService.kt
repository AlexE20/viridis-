package com.pdm.viridis.data.remote.userPlants


import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.responses.UserPlantRequest
import com.pdm.viridis.data.remote.responses.UserPlantResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserPlantService {

    @GET("api/userPlants/{userId}/{gardenId}")
    suspend fun getPlants(
        @Path("userId") userId: String,
        @Path("gardenId") gardenId: String
    ): List<UserPlantResponse>

    @POST("api/userPlants/{userId}/{gardenId}")
    suspend fun addPlant(
        @Path("userId") userId: String,
        @Path("gardenId") gardenId: String,
        @Body request: UserPlantRequest
    ): UserPlantResponse


    @DELETE("api/userPlants/{userPlantId}")
    suspend fun deletePlant(
        @Path("userPlantId") userPlantId: String
    ): UserPlantResponse
}