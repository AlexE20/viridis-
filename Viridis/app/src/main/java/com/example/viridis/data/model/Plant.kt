package com.example.viridis.data.model

import java.util.Date

data class Plant(
    val id:String,
    val name: String,
    val scientificName:String,
    val watering:String,
    val recommendations: List<String>,
    val imageUrl:String,
    val streak: Int,
    val lastWatered: String,
    val idGarden:Int,
    val difficulty:String,
    val shadeLevel:Int
)