package com.example.viridis.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.viridis.data.local.AppDatabase
import com.example.viridis.data.local.GardenDao
import kotlinx.coroutines.flow.first

class AppProvider private constructor(context: Context) {

    // --- DataStore ---
    private val Context.dataStore by preferencesDataStore(name = "app_preferences")
    private val dataStore = context.dataStore
    private val tokenKey = stringPreferencesKey("auth_token")

    // --- Room ---
    private val database: AppDatabase = AppDatabase.getDatabase(context)
    val gardenDao: GardenDao = database.gardenDao()

    // --- Métodos para el token ---
    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    suspend fun getToken(): String? {
        val prefs = dataStore.data.first()
        return prefs[tokenKey]
    }

    // --- Singleton de provider ---
    companion object {
        @Volatile
        private var INSTANCE: AppProvider? = null

        fun getInstance(context: Context): AppProvider {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppProvider(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
