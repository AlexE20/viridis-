package com.example.viridis.data.repository.Garden


import com.example.viridis.data.model.Garden

import kotlinx.coroutines.flow.Flow


interface GardenRepository{
    suspend fun getGardens(userId: String): List<Garden>
    suspend fun addGarden(userId: String, garden: Garden): List<Garden>
    suspend fun deleteGarden(userId: String, gardenId: String): List<Garden>
    fun getLocalGardens(): Flow<List<Garden>>
    suspend fun saveLocalGardens(userId: String)
}

