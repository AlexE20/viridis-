package com.pdm.viridis.data.repository.Plant

import com.pdm.viridis.data.database.daos.PlantDao
import com.pdm.viridis.data.database.entities.PlantEntity
import com.pdm.viridis.data.mappers.toModel
import com.pdm.viridis.data.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantRepositoryImpl(
    private val plantDao: PlantDao
) : PlantRepository {

    override fun getPlantsByGarden(gardenId: Int): Flow<List<PlantEntity>> {
        return plantDao.getPlantsByGarden(gardenId)
    }

    override suspend fun addPlant(plant: PlantEntity) {
        plantDao.addPlant(plant)
    }

    override suspend fun deletePlant(plant: PlantEntity) {
        plantDao.deletePlant(plant)
    }

    override fun getPlantsFlow(): Flow<List<Plant>> {
        return plantDao.getAllPlants().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun getPlants() : List<Plant> {
        return plantDao.getAllPlantsOnce().map { it.toModel() }
    }
}