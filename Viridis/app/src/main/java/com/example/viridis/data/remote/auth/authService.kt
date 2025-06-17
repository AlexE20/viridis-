package com.example.viridis.data.remote


import com.example.viridis.data.remote.responses.SignUpRequest
import com.example.viridis.data.remote.responses.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST



interface AuthService {
        @POST("api/auth/register")
        suspend fun register(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}


