package com.pdm.viridis.data.model

data class UserPlant(
    val id: String? = null,
    val plantId: String,
    val gardenId: String,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<String>,
    val wateredStreak: Int = 0,
    val lastWateredAt: Long = 0L
)
