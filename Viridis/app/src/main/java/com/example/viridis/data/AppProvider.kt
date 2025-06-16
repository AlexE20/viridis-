package com.example.viridis.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.viridis.data.database.AppDatabase
import com.example.viridis.data.repository.gardenRepository.GardenRepository
import com.example.viridis.data.repository.gardenRepository.GardenRepositoryImpl
import com.example.viridis.data.repository.plantRepository.PlantRepository
import com.example.viridis.data.repository.plantRepository.PlantRepositoryImpl
import com.example.viridis.data.repository.UserPreferencesRepository

class AppProvider (context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val plantDao = appDatabase.plantDao()
    private val gardenDao = appDatabase.gardenDao()
    private val gardenRepository: GardenRepository = GardenRepositoryImpl(gardenDao)
    private val plantRepository: PlantRepository = PlantRepositoryImpl(plantDao)

    fun provideGardenRepository(): GardenRepository = gardenRepository

    fun providePlantRepository(): PlantRepository = plantRepository

//    fun provideUserPreferencesRepository(): UserPreferencesRepository {
//        return UserPreferencesRepository(dataStore)
//    }


}
