package com.example.viridis.data.repository.plantRepository

import com.example.viridis.data.database.entities.PlantEntity
import com.example.viridis.data.model.Plant
import com.example.viridis.dummyData.dummyPlants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface PlantRepository{
    fun getPlantsByGarden(gardenId: Int): Flow<List<PlantEntity>>
    suspend fun addPlant(plant: PlantEntity)
    suspend fun deletePlant(plant: PlantEntity)
    fun getPlantsFlow(): Flow<List<Plant>>

//    suspend fun getPlants():List<Plant>
//    suspend fun getPlantsByGarden(gardenId:Int):List<Plant>
//    suspend fun addPlant(plant:Plant):List<Plant>
//    suspend fun deletePlant(plant:Plant): List<Plant>
}
