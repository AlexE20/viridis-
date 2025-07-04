package com.pdm.viridis.data.repository.Auth

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.pdm.viridis.data.remote.AuthService

import com.pdm.viridis.data.remote.responses.SignUpRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.pdm.viridis.data.remote.RetrofitInstance
import com.pdm.viridis.data.remote.responses.FcmTokenRequest
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

            val token = user.getIdToken(true).await().token
                ?: return Result.failure(Exception("Token is null"))
            val uid = user.uid

            val signUpRequest = SignUpRequest(uid, email, username)
            authService.register(signUpRequest)

            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.d("FCM", "✅ Manual FCM token: $token")

                    CoroutineScope(Dispatchers.IO).launch {
                        try {



                            val response = RetrofitInstance.notificationService.updateFcmToken(
                                FcmTokenRequest(uid, token)
                            )

                            if (response.isSuccessful) {
                                Log.d("FCM", "✅ Token sent to server (manual)")
                            } else {
                                Log.e("FCM", "❌ Server error: ${response.code()} - ${response.message()}")
                            }

                        } catch (e: Exception) {
                            Log.e("FCM", "❌ Error sending token: ${e.localizedMessage}")
                        }
                    }
                } else {
                    Log.w("FCM", "❌ Token fetch failed", task.exception)
                }
            }

            clearToken()
            saveToken(token)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String) : String{
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password).await()
        val firebaseToken = auth.currentUser?.getIdToken(false)?.await()?.token
            ?: throw Exception("Failed to retrieve Firebase token")

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM", "✅ Manual FCM token: $token")

                CoroutineScope(Dispatchers.IO).launch {
                    try {


                        val userId = extractUidFromToken(firebaseToken) ?: return@launch

                        val response = RetrofitInstance.notificationService.updateFcmToken(
                            FcmTokenRequest(userId, token)
                        )

                        if (response.isSuccessful) {
                            Log.d("FCM", "✅ Token sent to server (manual)")
                        } else {
                            Log.e("FCM", "❌ Server error: ${response.code()} - ${response.message()}")
                        }

                    } catch (e: Exception) {
                        Log.e("FCM", "❌ Error sending token: ${e.localizedMessage}")
                    }
                }
            } else {
                Log.w("FCM", "❌ Token fetch failed", task.exception)
            }
        }




        return firebaseToken
    }





}