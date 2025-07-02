package com.pdm.viridis.data.repository.Garden

import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.remote.responses.GardenRequest
import com.pdm.viridis.data.remote.responses.GardenResponse

import kotlinx.coroutines.flow.Flow

interface GardenRepository{
    suspend fun getGardens(userId: String): List<Garden>
    suspend fun addGarden(userId: String, garden: GardenRequest) : Garden
    suspend fun deleteGarden(userId: String, gardenId: String)
    suspend fun saveLocalGardens(userId: String)
    fun getLocalGardens(userId: String): Flow<List<Garden>>
    suspend fun getGarden(userId: String) : Garden
}

