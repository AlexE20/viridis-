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

class AppProvider (
    context: Context,
    private val dataStore: DataStore<Preferences>
) {
    private val appDatabase = AppDatabase.getDatabase(context)

    fun provideGardenRepository(): GardenRepository {
        return GardenRepositoryImpl(appDatabase.gardenDao())
    }

    fun providePlantRepository(): PlantRepository {
        return PlantRepositoryImpl(appDatabase.plantDao())
    }

    fun provideUserPreferencesRepository(): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }


}
