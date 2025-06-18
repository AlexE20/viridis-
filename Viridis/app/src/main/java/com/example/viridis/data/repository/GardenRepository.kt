package com.example.viridis.data.repository

import com.example.viridis.Retrofit.GardenService
import com.example.viridis.Retrofit.toDomain
import com.example.viridis.data.local.GardenDao
import com.example.viridis.data.local.GardenEntity
import com.example.viridis.data.model.Garden
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GardenRepository{
    suspend fun getGardens():List<Garden>


}

class GardenRepositoryImpl(private val gardenDao: GardenDao,private val api: GardenService): GardenRepository{


    override suspend fun getGardens(): List<Garden> {
        val user= "lM3KBVNcEgRWL0LbDJ9wcp5CPWV2"
        delay(1000)
        return api.getGardens(user).map{it.toDomain()}
    }

}