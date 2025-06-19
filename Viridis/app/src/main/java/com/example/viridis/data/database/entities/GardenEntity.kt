package com.example.viridis.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Garden")
data class GardenEntity(
    @PrimaryKey val id: String,
    val idUser: String,
    val name: String,
    val shadeLevel: String
)
