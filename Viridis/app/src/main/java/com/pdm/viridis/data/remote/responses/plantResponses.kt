package com.pdm.viridis.data.remote.responses
import com.pdm.viridis.data.model.Recommendation

data class PlantResponse(
    val id:String?,
    val name: String?,
    val scientificName:String?,
    val watering:String?,
    val recommendations: List<Recommendation>?,
    val imageUrl:String?,
    val careLevel:String?,
    val shadeLevel:String?
)

