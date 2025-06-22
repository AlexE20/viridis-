package com.pdm.viridis.data.repository.Plant

import com.pdm.viridis.data.database.daos.PlantDao
import com.pdm.viridis.data.database.entities.PlantEntity
import com.pdm.viridis.data.mappers.toModel
import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.remote.plants.PlantService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantRepositoryImpl(
    private val plantService: PlantService
) : PlantRepository {



    override suspend fun getCatalogPlants(limit: Int?, startAfter: String?): List<Plant> {
        return try {
            plantService.getCatalogPlants(
                limit = limit ?: 20,
                startAfter = startAfter
            )
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 404) {
                emptyList()
            } else {
                throw e
            }
        }
    }

    override suspend fun getCatalogPlantsByName(name: String): List<Plant> {
        return try {
            plantService.getCatalogPlantsByName(name)

        } catch (e: retrofit2.HttpException) {

            throw e

        }
    }


}