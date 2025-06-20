package com.pdm.viridis.data.repository.Auth

import kotlinx.coroutines.flow.Flow

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