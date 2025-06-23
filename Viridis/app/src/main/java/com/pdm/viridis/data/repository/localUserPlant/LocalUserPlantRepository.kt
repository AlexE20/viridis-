package com.pdm.viridis.data.repository.localUserPlant

import com.pdm.viridis.data.model.UserPlant
import kotlinx.coroutines.flow.Flow

interface LocalUserPlantRepository {
    suspend fun addPlant(gardenId: String, plant: UserPlant)
    fun getPlants(gardenId: String): Flow<List<UserPlant>>
    suspend fun deletePlant(userPlantId: String)
}
