package com.pdm.viridis.data.remote


import com.pdm.viridis.data.remote.responses.SignUpRequest
import com.pdm.viridis.data.remote.responses.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface AuthService {
        @POST("api/auth/register")
        suspend fun register(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}


