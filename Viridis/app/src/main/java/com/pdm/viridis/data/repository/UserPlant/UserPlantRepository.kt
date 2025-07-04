package com.pdm.viridis.data.repository.UserPlant

import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.responses.UserPlantRequest
import kotlinx.coroutines.flow.Flow

interface UserPlantRepository {
    suspend fun getPlants(userId: String,gardenId: String): List<UserPlant>
    suspend fun addPlant(userId: String, gardenId: String, request: UserPlantRequest): UserPlant
    suspend fun deletePlant(userId: String, userPlantId: String)
    fun getLocalPlants(gardenId: String): Flow<List<UserPlant>>
    suspend fun saveLocalPlants(userId: String, gardenId: String)
}