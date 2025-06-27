package com.pdm.viridis.data.remote.responses

data class UserPlantRequest(
    val id: String
)

data class UserPlantResponse(
    val id: String,
    val plant_id: String,
    val user_id: String,
    val garden_id: String,
    val common_name: String,
    val scientific_name: List<String>,
    val care_level: String,
    val watering: String,
    val default_image: String,
    val recommendations: List<RecommendationResponse>,
    val last_watered: String,
    val streak: Int,
    val shade_level: String
)