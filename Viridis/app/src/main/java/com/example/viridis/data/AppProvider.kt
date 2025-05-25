package com.example.viridis.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider(context: Context) {
    private lateinit var appContext: Context



    //Constructor
    fun init(context: Context) {
        appContext = context.applicationContext

    }

    fun getContext(): Context = appContext


    // --- DS Helpers ---
    private val authTokenKey = stringPreferencesKey("auth-token")

    suspend fun saveToken(token: String) {
        appContext.dataStore.edit { prefs ->
            prefs[authTokenKey] = token
        }
    }

    fun getToken(): Flow<String?> {
        return appContext.dataStore.data.map { prefs ->
            prefs[authTokenKey]
        }
    }

    suspend fun clearToken() {
        appContext.dataStore.edit { it.remove(authTokenKey) }
    }




}