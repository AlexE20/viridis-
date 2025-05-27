package com.example.viridis.data.repository

import com.example.viridis.data.local.GardenDao
import com.example.viridis.data.local.GardenEntity
import com.example.viridis.data.model.Garden
import com.example.viridis.dummyData.dummyGardens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GardenRepository{
    suspend fun getGardens():List<Garden>
    suspend fun addGarden(garden:Garden):List<Garden>
    suspend fun deleteGarden(garden:Garden):List<Garden>
    fun getLocalGardens(): Flow<List<Garden>>
    suspend fun saveLocalGardens()

}

class GardenRepositoryImpl(private val gardenDao: GardenDao): GardenRepository{
    private var gardens=mutableListOf<Garden>().apply{
        addAll(dummyGardens)
    }

    override suspend fun getGardens(): List<Garden> {
        delay(1000)
        return gardens
    }

    override suspend fun addGarden(garden:Garden): List<Garden> {
        delay(1000)
        gardens.add(garden)
        return gardens
    }

    override suspend fun deleteGarden(garden:Garden): List<Garden> {
        delay(1000)
        gardens.remove(garden)
        return gardens
    }

    override fun getLocalGardens(): Flow<List<Garden>> {
        return gardenDao.getAllGardens().map{ list->
            list.map{entity->
                Garden(
                    id=entity.id,
                    name=entity.name,
                    user = entity.user,
                    shade=entity.shade
                )
            }
        }
    }

    override suspend fun saveLocalGardens() {
        delay(1000)
        val entities=gardens.map{garden->
            GardenEntity(
                id=garden.id,
                name=garden.name,
                user = garden.user,
                shade=garden.shade

            )

        }
        gardenDao.addGardens(entities)
    }

}