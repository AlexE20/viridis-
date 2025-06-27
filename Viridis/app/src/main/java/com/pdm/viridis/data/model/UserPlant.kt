package com.pdm.viridis.data.model

data class UserPlant(
    val id: String? = null,
    val plantId: String,
    val gardenId: String? = null,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String? = null,
    val shadeLevel:String? = null,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<Recommendation>,
    val wateredStreak: Int = 0,
    val lastWateredAt: String?= null
)
