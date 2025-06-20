package com.pdm.viridis.data.repository.gardenRepository

import com.pdm.viridis.data.database.daos.GardenDao
import com.pdm.viridis.data.database.entities.GardenEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.pdm.viridis.data.mappers.toModel
import com.pdm.viridis.data.model.Garden

class GardenRepositoryImpl(private val gardenDao: GardenDao) : GardenRepository {

    override fun getGardensByUser(userId: String): Flow<List<GardenEntity>> = flow {
        emit(gardenDao.getGardensByUser(userId))
    }

    override suspend fun addGarden(garden: GardenEntity) {
        gardenDao.addGarden(garden)
    }

    override suspend fun deleteGarden(gardenId: String) {
        gardenDao.deleteGarden(gardenId)
    }

    override fun getLocalGardens(): Flow<List<Garden>> {
        return gardenDao.getAllGardens().map { list ->
            list.map { it.toModel() }
        }
    }
}