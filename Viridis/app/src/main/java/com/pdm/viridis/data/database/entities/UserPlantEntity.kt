package com.pdm.viridis.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_plants",
    
)
data class UserPlantEntity(
    @PrimaryKey val id: String,
    val gardenId: String,
    val plantId: String,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<String>,
    val wateredStreak: Int = 0,
    val lastWateredAt: String
)


