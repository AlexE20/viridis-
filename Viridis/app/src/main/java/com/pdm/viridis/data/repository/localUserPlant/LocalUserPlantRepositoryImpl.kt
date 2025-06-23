package com.pdm.viridis.data.repository.localUserPlant

import com.pdm.viridis.data.database.daos.PlantDao
import com.pdm.viridis.data.mappers.toEntity
import com.pdm.viridis.data.mappers.toModel
import com.pdm.viridis.data.model.UserPlant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserPlantRepositoryImpl(
    private val dao: PlantDao
) : LocalUserPlantRepository {

    override suspend fun addPlant(gardenId: String, plant: UserPlant) {
        dao.addPlant(plant.toEntity(gardenId))
    }

    override fun getPlants(gardenId: String): Flow<List<UserPlant>> =
        dao.getPlantsByGarden(gardenId).map { list -> list.map { it.toModel() } }

    override suspend fun deletePlant(userPlantId: String) {
        dao.delete(userPlantId)
    }
}