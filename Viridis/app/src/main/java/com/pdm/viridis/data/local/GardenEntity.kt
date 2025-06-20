package com.pdm.viridis.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Garden")
data class GardenEntity(
    @PrimaryKey val id:String,
    val name:String,
    val idUser: String,
    val shadeLevel:String,
)