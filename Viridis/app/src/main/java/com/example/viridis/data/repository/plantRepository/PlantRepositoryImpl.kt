package com.example.viridis.data.repository.plantRepository

import com.example.viridis.data.database.daos.PlantDao
import com.example.viridis.data.model.Plant
import com.example.viridis.dummyData.dummyPlants
import kotlinx.coroutines.delay

class PlantRepositoryImpl(private val plantDao: PlantDao): PlantRepository {
    private var plants=mutableListOf<Plant>().apply {
        addAll(dummyPlants)
    }

    override suspend fun getPlants(): List<Plant> {
        return plants
    }

    override suspend fun getPlantsByGarden(gardenId: Int): List<Plant> {

        return dummyPlants.filter {
            it.idGarden == gardenId
        }
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