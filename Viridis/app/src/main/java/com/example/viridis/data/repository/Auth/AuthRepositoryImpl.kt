package com.example.viridis.data.repository.Auth

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.viridis.data.remote.AuthService

import com.example.viridis.data.remote.responses.SignUpRequest
import com.example.viridis.data.remote.responses.SignUpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.io.IOException

class AuthRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val authService: AuthService
) : AuthRepository {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("AUTH_TOKEN")
        private val USERNAME = stringPreferencesKey("USERNAME")
    }



    override val token: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[TOKEN_KEY]
        }


    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override val username: Flow<String?> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { preferences -> preferences[USERNAME] }

    override suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }


    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Result<Unit> {
        return try {
            val authResult = FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = authResult.user ?: return Result.failure(Exception("User not found"))






            val uid = user.uid

            val signUpRequest = SignUpRequest(uid, email, username)
            authService.register(signUpRequest)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }





}