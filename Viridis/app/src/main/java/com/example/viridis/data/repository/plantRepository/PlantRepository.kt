package com.example.viridis.data.repository.plantRepository

import com.example.viridis.data.model.Plant
import com.example.viridis.dummyData.dummyPlants
import kotlinx.coroutines.delay

interface PlantRepository{
    suspend fun getPlants():List<Plant>
    suspend fun getPlantsByGarden(gardenId:Int):List<Plant>
    suspend fun addPlant(plant:Plant):List<Plant>
    suspend fun deletePlant(plant:Plant): List<Plant>
}
