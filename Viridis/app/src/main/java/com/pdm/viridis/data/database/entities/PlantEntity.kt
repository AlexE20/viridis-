package com.pdm.viridis.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "Plant",
    foreignKeys = [
        ForeignKey(
            entity = GardenEntity::class,
            parentColumns = ["id"],
            childColumns = ["gardenId"],
            onDelete = CASCADE
        )
    ]
)
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gardenId: String,
    val plantId: String,
    val commonName: String,
    val scientificName: List<String>,
    val careLevel: String,
    val watering: String,
    val defaultImage: String,
    val recommendations: List<String>
)

