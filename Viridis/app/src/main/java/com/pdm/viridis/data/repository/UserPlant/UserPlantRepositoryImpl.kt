package com.pdm.viridis.data.repository.UserPlant

import com.pdm.viridis.data.database.daos.PlantDao
import com.pdm.viridis.data.mappers.toDomain
import com.pdm.viridis.data.mappers.toEntity
import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.responses.UserPlantRequest
import com.pdm.viridis.data.remote.userPlants.UserPlantService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPlantRepositoryImpl(
    private val dao: PlantDao,
    private val service: UserPlantService
) : UserPlantRepository {

    private var plants = emptyList<UserPlant>()

    override suspend fun getPlants(userId: String, gardenId: String): List<UserPlant> {
        val remote = service.getPlants(userId, gardenId)
        plants = remote.map { it.toDomain() }
        return plants
    }

    override suspend fun addPlant(
        userId: String,
        gardenId: String,
        request: UserPlantRequest
    ): UserPlant {
        val added = service.addPlant(userId, gardenId, request).toDomain()
        plants = plants + added
        dao.addPlant(added.toEntity())
        return added
    }

    override suspend fun deletePlant(
        userId: String,
        userPlantId: String
    ) {
        service.deletePlant(userPlantId)
        plants = plants.filterNot { it.id == userPlantId }
        dao.delete(userPlantId)
    }

    override fun getLocalPlants(gardenId: String): Flow<List<UserPlant>> =
        dao.getPlantsByGarden(gardenId).map { list -> list.map { it.toDomain() } }

    override suspend fun saveLocalPlants(userId: String, gardenId: String) {
        val remote = service.getPlants(userId, gardenId)
        plants = remote.map { it.toDomain() }
        dao.deletePlantsFromGarden(gardenId)
        dao.addPlants(remote.map { it.toDomain().toEntity() })
    }
}
