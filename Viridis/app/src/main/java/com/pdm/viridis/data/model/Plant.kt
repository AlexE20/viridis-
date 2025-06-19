package com.pdm.viridis.data.model

data class Plant(
    val id:String,
    val name: String,
    val scientificName:String,
    val watering:String,
    val recommendations: List<String>,
    val imageUrl:String,
    val streak: Int,
    val lastWatered: String,
    val idGarden:String,
    val difficulty:String,
    val shadeLevel:String
)