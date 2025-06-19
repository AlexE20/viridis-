package com.example.viridis.data.repository.Plant

import com.example.viridis.data.model.UserPlant

interface UserPlantRepository {
    suspend fun getPlants(userId: String,gardenId: String): List<UserPlant>
}