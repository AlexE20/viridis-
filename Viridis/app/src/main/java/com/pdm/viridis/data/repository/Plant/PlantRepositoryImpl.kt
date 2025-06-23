package com.pdm.viridis.data.repository.Plant

import com.pdm.viridis.data.mappers.toModel
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.remote.plants.PlantService
import retrofit2.HttpException

class PlantRepositoryImpl(
    private val plantService: PlantService
) : PlantRepository {

    override suspend fun getCatalogPlants(limit: Int?, startAfter: String?): List<Plant> {
        return try {
            plantService.getCatalogPlants(
                limit = limit ?: 20,
                startAfter = startAfter
            ).map { it.toModel() }
        } catch (e: HttpException) {
            if (e.code() == 404) emptyList() else throw e
        }
    }

    override suspend fun getCatalogPlantsByName(name: String): List<Plant> {
        return try {
            plantService.getCatalogPlantsByName(name).map { it.toModel() }
            val result = plantService.getCatalogPlantsByName(name).map { it.toModel() }
            println("🧪 Mapped recommendations sizes: ${result.map { it.recommendations.size }}")
            return result

        } catch (e: HttpException) {
            if (e.code() == 404) emptyList() else throw e
        }
    }
}
