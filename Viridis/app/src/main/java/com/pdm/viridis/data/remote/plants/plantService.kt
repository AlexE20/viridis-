package com.pdm.viridis.data.remote.plants


import com.pdm.viridis.data.model.Plant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantService {

    @GET("api/plants")
    suspend fun getCatalogPlants(
        @Query("limit") limit: Int?=10,
        @Query("startAfter") startAfter: String? = null
    ): List<Plant>


    @GET("api/plants/{plantName}")
    suspend fun getCatalogPlantsByName(
        @Path("plantName") plantName: String,
    ):List<Plant>


}