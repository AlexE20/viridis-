package com.pdm.viridis.data.repository.UserPlant

import com.pdm.viridis.data.model.UserPlant

interface UserPlantRepository {
    suspend fun getPlants(userId: String,gardenId: String): List<UserPlant>
}