package com.example.viridis.data.repository

import com.example.viridis.data.model.Plant
import com.example.viridis.dummyData.dummyPlants
import kotlinx.coroutines.delay

interface PlantRepository{
    suspend fun getPlants():List<Plant>
    suspend fun addPlant(plant:Plant):List<Plant>
    suspend fun deletePlant(plant:Plant): List<Plant>
}

class PlantRepositoryImpl: PlantRepository{
    private var plants=mutableListOf<Plant>().apply {
        addAll(dummyPlants)
    }

    override suspend fun getPlants(): List<Plant> {
     delay(1000)
       return plants
    }



    override suspend fun addPlant(plant: Plant): List<Plant> {
        delay(1000)
        plants.add(plant)
        return plants
    }

    override suspend fun deletePlant(plant: Plant): List<Plant> {
        delay(1000)
        plants.remove(plant)
        return plants
    }


}