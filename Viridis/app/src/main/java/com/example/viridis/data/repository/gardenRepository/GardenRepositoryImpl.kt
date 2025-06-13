package com.example.viridis.data.repository.gardenRepository

import com.example.viridis.data.database.daos.GardenDao
import com.example.viridis.data.database.entities.GardenEntity
import com.example.viridis.data.model.Garden
import com.example.viridis.dummyData.dummyGardens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GardenRepositoryImpl(private val gardenDao: GardenDao): GardenRepository {
    private var gardens=mutableListOf<Garden>().apply{
        addAll(dummyGardens)
    }

    override suspend fun getGardens(): List<Garden> {
        delay(1000)
        return gardens
    }

//    override suspend fun getGardens(): List<Garden> {
//        return gardenDao.getAllGardens().first().map { entity ->
//            Garden(id = entity.id, name = entity.name, user = entity.idUser, shade = entity.shadeLevel)
//        }
//    }


    override suspend fun addGarden(garden: Garden): List<Garden> {
        delay(1000)
        gardens.add(garden)
        return gardens
    }

    override suspend fun deleteGarden(garden: Garden): List<Garden> {
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
                    user = entity.idUser,
                    shade=entity.shadeLevel
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
                idUser = garden.user,
                shadeLevel = garden.shade

            )

        }
        gardenDao.addGardens(entities)
    }

}