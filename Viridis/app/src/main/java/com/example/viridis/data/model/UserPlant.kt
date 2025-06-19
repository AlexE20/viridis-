package com.example.viridis.data.model

data class UserPlant(
    val id: String?,
    val plant_id: String?,
    val common_name: String?,
    val scientific_name: List<String>?,
    val care_level: String?,
    val watering: String?,
    val default_image: String?,
    val recommendations: List<Recommendation>?,
)