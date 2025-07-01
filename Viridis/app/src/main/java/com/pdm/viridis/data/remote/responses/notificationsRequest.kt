package com.pdm.viridis.data.remote.responses


data class FcmTokenRequest(
    val uid: String,
    val fcmToken: String
)