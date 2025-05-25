package com.example.viridis.data.repository



import com.example.viridis.data.AppProvider
import kotlinx.coroutines.flow.Flow

class AuthRepository {
    //TODO
    suspend fun login(email: String, password: String): Result<String> {
        // Replace with real authentication call
        return if (email == "test@viridis.com" && password == "123456") {
            val token = "fake_token_abc123"
            //AppProvider.saveToken()
            Result.success(token)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    suspend fun saveToken(token: String) {
        //AppProvider.saveToken(token)
    }

    //fun getToken(): Flow<String?> = AppProvider.getToken()

    suspend fun logout() {
        //AppProvider.clearToken()
    }
}
