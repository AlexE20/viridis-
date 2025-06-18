package com.example.viridis.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.viridis.Retrofit.gardenService
import com.example.viridis.data.local.AppDatabase
import com.example.viridis.data.local.GardenDao
import com.example.viridis.data.repository.GardenRepository
import com.example.viridis.data.repository.GardenRepositoryImpl
import com.example.viridis.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first

class AppProvider(
    context: Context,
    private val dataStore: DataStore<Preferences>
) {

    private val appDatabase = AppDatabase.getDatabase(context)

    fun provideGardenRepository(): GardenRepository {
        return GardenRepositoryImpl(appDatabase.gardenDao(), gardenService)
    }

    fun provideUserPreferencesRepository(): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }


}
