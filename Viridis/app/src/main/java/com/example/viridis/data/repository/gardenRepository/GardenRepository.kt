package com.example.viridis.data.repository.gardenRepository

import com.example.viridis.data.model.Garden
import kotlinx.coroutines.flow.Flow

interface GardenRepository{
    suspend fun getGardens():List<Garden>
    suspend fun addGarden(garden:Garden):List<Garden>
    suspend fun deleteGarden(garden:Garden):List<Garden>
    fun getLocalGardens(): Flow<List<Garden>>
    suspend fun saveLocalGardens()
}
