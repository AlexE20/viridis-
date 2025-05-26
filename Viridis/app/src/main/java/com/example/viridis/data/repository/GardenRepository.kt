package com.example.viridis.data.repository

import com.example.viridis.data.model.Garden
import com.example.viridis.dummyData.dummyGardens
import kotlinx.coroutines.delay

interface GardenRepository{
    suspend fun getGardens():List<Garden>
    suspend fun addGarden(garden:Garden):List<Garden>
    suspend fun deleteGarden(garden:Garden):List<Garden>
}

class GardenRepositoryImpl: GardenRepository{
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

}