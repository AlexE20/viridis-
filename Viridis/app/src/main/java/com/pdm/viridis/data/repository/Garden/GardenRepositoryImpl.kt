package com.pdm.viridis.data.repository.Garden

import com.pdm.viridis.data.local.GardenDao
import com.pdm.viridis.data.local.GardenEntity
import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.remote.gardens.GardenService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GardenRepositoryImpl(
    private val gardenDao: GardenDao,
    private val gardenService: GardenService
) : GardenRepository {

    private var gardens = emptyList<Garden>()

    override suspend fun getGardens(userId: String): List<Garden> {
        return try {
            gardens = gardenService.getGardens(userId)
            gardens
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 404) {
                // Backend says "no gardens yet", return empty list gracefully
                emptyList()
            } else {
                throw e // rethrow other unexpected errors
            }
        }
    }

    override suspend fun addGarden(userId: String, garden: Garden): List<Garden> {
        val addedGarden = gardenService.addGarden(userId, garden)
        gardens = gardens + addedGarden

        val entity = GardenEntity(
            id = addedGarden.id,
            name = addedGarden.name,
            user = addedGarden.idUser,
            shade = addedGarden.shadeLevel
        )
        gardenDao.addGardens(listOf(entity))
        return gardens
    }

    override suspend fun deleteGarden(userId: String, gardenId: String): List<Garden> {
        gardenService.deleteGarden(userId, gardenId)
        gardens = gardens.filter { it.id != gardenId }
        gardenDao.deleteGardenById(gardenId)
        return gardens
    }

    override fun getLocalGardens(): Flow<List<Garden>> {
        return gardenDao.getAllGardens().map { list ->
            list.map { entity ->
                Garden(
                    id = entity.id,
                    name = entity.name,
                    idUser = entity.id,
                    shadeLevel = entity.shade
                )
            }
        }
    }

    override suspend fun saveLocalGardens(userId: String) {
        val remoteGardens = gardenService.getGardens(userId)
        gardens = remoteGardens

        val entities = remoteGardens.map { garden ->
            GardenEntity(
                id = garden.id,
                name = garden.name,
                user = garden.idUser,           // Use idUser here, not garden.user
                shade = garden.shadeLevel       // Use shadeLevel to match JSON
            )
        }

        gardenDao.clearAllGardens()
        gardenDao.addGardens(entities)
    }
}
