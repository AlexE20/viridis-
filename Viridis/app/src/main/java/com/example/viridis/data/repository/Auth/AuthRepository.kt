package com.example.viridis.data.repository.Auth

import com.example.viridis.data.remote.responses.SignUpResponse
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface AuthRepository {
    val token: Flow<String?>
    val username: Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun clearToken()
    suspend fun saveUsername(username: String)
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): Result<Unit>

}