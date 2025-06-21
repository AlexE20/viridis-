package com.pdm.viridis.data.repository.Plant





import com.pdm.viridis.data.database.entities.PlantEntity
import com.pdm.viridis.data.model.Plant

import kotlinx.coroutines.flow.Flow


interface PlantRepository{
    fun getPlantsByGarden(gardenId: Int): Flow<List<PlantEntity>>
    suspend fun addPlant(plant: PlantEntity)
    suspend fun deletePlant(plant: PlantEntity)
    fun getPlantsFlow(): Flow<List<Plant>>
    suspend fun getPlants() : List<Plant>
//    suspend fun getPlantsByGarden(gardenId:Int):List<Plant>
//    suspend fun addPlant(plant:Plant):List<Plant>
//    suspend fun deletePlant(plant:Plant): List<Plant>
}