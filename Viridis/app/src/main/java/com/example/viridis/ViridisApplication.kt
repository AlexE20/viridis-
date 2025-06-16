package com.example.viridis

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import com.example.viridis.data.AppProvider
import com.example.viridis.data.repository.UserPreferencesRepository

private const val AUTH_TOKEN = "token"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AUTH_TOKEN)

class ViridisApplication : Application() {
    val appProvider: AppProvider by lazy{
        AppProvider(this)
    }

    override fun onCreate() {
        super.onCreate()
        val gardenRepo = appProvider.provideGardenRepository()
        val plantRepo = appProvider.providePlantRepository()
    }
}
