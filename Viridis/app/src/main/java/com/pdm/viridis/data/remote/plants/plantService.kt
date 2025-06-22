package com.pdm.viridis.data.remote.plants

import com.pdm.viridis.data.remote.responses.PlantResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantService {
    @GET("api/plants")
    suspend fun getCatalogPlants(
        @Query("limit") limit: Int?=20,
        @Query("startAfter") startAfter: String? = null
    ): List<PlantResponse>

    @GET("api/plants/search")
    suspend fun getCatalogPlantsByName(
        @Query("plantName") plantName: String,
    ):List<PlantResponse>
}