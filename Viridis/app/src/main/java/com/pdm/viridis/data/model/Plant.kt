package com.pdm.viridis.data.model

data class Plant(
    val id:String?,
    val name: String?,
    val scientificName:String?,
    val watering:String?,
    val recommendations: List<Recommendation>?,
    val imageUrl:String?,
    val careLevel:String?,
    val shadeLevel:String?
)