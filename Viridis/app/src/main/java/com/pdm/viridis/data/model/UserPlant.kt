package com.pdm.viridis.data.model

data class UserPlant(
    val id: String?,
    val plantId: String,
    val gardenId: String?,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String? ,
    val shadeLevel:String?,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<Recommendation>,
    val wateredStreak: Int = 0,
    val lastWateredAt: String?
)
