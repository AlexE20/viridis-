package com.pdm.viridis.data.remote.responses





data class SignUpRequest(
    val uid: String,
    val email: String,
    val username: String
)


data class SignUpResponse(
    val userId: String,
    val message: String
)




