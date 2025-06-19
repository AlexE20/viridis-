package com.pdm.viridis.data.repository.gardenRepository

import com.pdm.viridis.data.database.entities.GardenEntity
import com.pdm.viridis.data.model.Garden
import kotlinx.coroutines.flow.Flow

interface GardenRepository{
    fun getGardensByUser(userId: String): Flow<List<GardenEntity>>
    suspend fun addGarden(garden: GardenEntity)
    suspend fun deleteGarden(garden: GardenEntity)
    fun getLocalGardens(): Flow<List<Garden>>

///////////////

//    suspend fun getGardens():List<Garden>
//    suspend fun saveLocalGardens()
}
