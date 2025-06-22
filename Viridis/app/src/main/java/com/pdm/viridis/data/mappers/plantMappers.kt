package com.pdm.viridis.data.mappers

import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.data.remote.responses.PlantResponse
import com.pdm.viridis.data.remote.responses.RecommendationResponse

fun PlantResponse.toModel(): Plant {
    return Plant(
        id = id,
        name = name,
        scientificName = scientificName?.firstOrNull() ?: "",
        watering = watering,
        recommendations = recommendations?.map { it.toModel() },
        imageUrl = imageUrl,
        careLevel = careLevel,
        shadeLevel = shadeLevel
    )
}

fun RecommendationResponse.toModel(): Recommendation {
    return Recommendation(
        id = id ?: -1,
        description = description.orEmpty(),
        type = type.orEmpty()
    )
}