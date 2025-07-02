package com.pdm.viridis.data.repository.Garden

import com.pdm.viridis.data.database.daos.GardenDao
import com.pdm.viridis.data.database.entities.GardenEntity
import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.remote.gardens.GardenService
import com.pdm.viridis.data.remote.responses.GardenRequest
import com.pdm.viridis.data.remote.responses.GardenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class GardenRepositoryImpl(
    private val gardenDao: GardenDao,
    private val gardenService: GardenService
) : GardenRepository {

    private var gardens = emptyList<Garden>()

    override suspend fun getGardens(userId: String): List<Garden> {
        return try {
            gardens = gardenService.getGardens(userId)
            gardens
        } catch (e: IOException) {
            emptyList()
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 404) emptyList() else throw e
        }
    }


    override suspend fun addGarden(userId: String, garden: GardenRequest): Garden{
        val addedGarden = gardenService.addGarden(userId,garden)
        gardens = gardens + addedGarden

        val entity = GardenEntity(
            id = addedGarden.id,
            idUser = addedGarden.idUser,
            name = addedGarden.name,
            shadeLevel = addedGarden.shadeLevel
        )
        gardenDao.addGarden(entity)
        return addedGarden
    }

    override suspend fun deleteGarden(userId: String, gardenId: String){
        gardenService.deleteGarden(userId, gardenId)
        gardens = gardens.filter { it.id != gardenId }
        gardenDao.deleteGarden(gardenId)

    }

    override fun getLocalGardens(userId: String): Flow<List<Garden>> {
        return gardenDao.getGardensByUser(userId).map { list ->
            list.map { entity ->
                Garden(
                    id = entity.id,
                    idUser = entity.idUser,
                    name = entity.name,
                    shadeLevel = entity.shadeLevel
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
                idUser = garden.idUser,
                name = garden.name,
                shadeLevel = garden.shadeLevel
            )
        }

        gardenDao.addGardens(entities)
    }
}
