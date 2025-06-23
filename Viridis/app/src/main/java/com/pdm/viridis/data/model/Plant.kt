package com.pdm.viridis.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Plant(
    val id:String?,
    val name: String? = null,
    val scientificName:String? = null,
    val watering:String? = null,
    val recommendations: List<Recommendation>? = null,
    val imageUrl:String? = null,
    val careLevel:String? = null,
    val shadeLevel:String? = null
): java.io.Serializable