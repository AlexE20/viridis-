package com.example.viridis.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Garden")
data class GardenEntity(
    @PrimaryKey val id:String,
    val name:String,
    val user: String,
    val shade:String,
)