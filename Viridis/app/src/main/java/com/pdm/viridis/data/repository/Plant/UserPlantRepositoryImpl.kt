package com.pdm.viridis.data.repository.Plant

import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.plants.UserPlantService

class UserPlantRepositoryImpl(private val userPlantService: UserPlantService) :
    UserPlantRepository {
    private var plants = emptyList<UserPlant>()
    override suspend fun getPlants(
        userId: String,
        gardenId: String
    ): List<UserPlant> {
        return try {
            plants = userPlantService.getPlants(userId, gardenId)
            plants
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 404) {
                emptyList()
            } else {
                throw e
            }
        }
    }
}