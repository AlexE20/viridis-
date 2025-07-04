package com.pdm.viridis

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.FirebaseApp
import com.pdm.viridis.data.AppProvider

private const val AUTH_TOKEN = "token"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AUTH_TOKEN)

class ViridisApplication : Application() {

    val appProvider: AppProvider by lazy{
        FirebaseApp.initializeApp(this)
        AppProvider(this,this.dataStore)
    }
}
