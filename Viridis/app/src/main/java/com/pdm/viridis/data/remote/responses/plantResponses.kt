package com.pdm.viridis.data.remote.responses
import com.pdm.viridis.data.model.Recommendation
import com.google.gson.annotations.SerializedName

data class PlantResponse(
    val id: String?,
    @SerializedName("common_name") val name: String?,
    @SerializedName("scientific_name") val scientificName: List<String>?,
    @SerializedName("care_level") val careLevel: String?,
    val watering: String?,
    @SerializedName("default_image") val imageUrl: String?,
    @SerializedName("shade_level") val shadeLevel: String?,
    val recommendations: List<RecommendationResponse>?
)


