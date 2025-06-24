package com.pdm.viridis.data.remote.responses

data class UserPlantRequest(
    val gardenId: String,
    val userPlantId: String,
    val plantId: String
)

data class UserPlantResponse(
    val id: String,
    val gardenId: String,
    val plantId: String,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<RecommendationResponse>,
    val wateredStreak: Int,
    val lastWateredAt: Long
)