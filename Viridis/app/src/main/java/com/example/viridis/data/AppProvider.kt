
package com.example.viridis.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.viridis.data.remote.RetrofitInstance
import com.example.viridis.data.repository.Auth.AuthRepository
import com.example.viridis.data.repository.Auth.AuthRepositoryImpl
import kotlinx.coroutines.flow.first

private const val USER_PREFERENCE_NAME = "user_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider(context: Context) {


    private val authService = RetrofitInstance.authService





    private val authRepository: AuthRepository = AuthRepositoryImpl(
        authService = authService,
        dataStore = context.dataStore
    )




    fun provideAuthRepository(): AuthRepository = authRepository
}

