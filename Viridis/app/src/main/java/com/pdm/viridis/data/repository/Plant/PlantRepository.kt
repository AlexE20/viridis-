package com.pdm.viridis.data.repository.Plant

import com.pdm.viridis.data.database.entities.PlantEntity
import com.pdm.viridis.data.model.Plant

import kotlinx.coroutines.flow.Flow

interface PlantRepository{
suspend fun getCatalogPlants(limit:Int?,startAfter:String?): List<Plant>
suspend fun getCatalogPlantsByName(name: String): List<Plant>
}