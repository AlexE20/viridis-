package com.pdm.viridis.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Recommendation(
    val id: Int,
    val description: String,
    val type: String
)