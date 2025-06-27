package com.pdm.viridis.data.mappers

import com.pdm.viridis.data.database.entities.UserPlantEntity
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.responses.RecommendationResponse
import com.pdm.viridis.data.remote.responses.UserPlantResponse
import java.util.UUID

fun UserPlantResponse.toDomain() = UserPlant(
    id = id,
    plantId = plant_id,
    gardenId = garden_id,
    commonName = common_name,
    scientificName = scientific_name,
    careLevel = care_level,
    watering = watering,
    defaultImage = default_image,
    recommendations = recommendations.map { it.toDomain() },
    wateredStreak = streak,
    lastWateredAt = last_watered
)

fun RecommendationResponse.toDomain() = Recommendation(
    description = description ?: "",
    id = id ?: 0,
    type = type ?: ""
)



fun UserPlant.toEntity() = UserPlantEntity(
    id = id ?: "",
    gardenId=gardenId?:"",
    plantId = plantId,
    commonName = commonName,
    scientificName = scientificName,
    careLevel = careLevel ?: "",
    watering = watering,
    defaultImage = defaultImage,
    recommendations = recommendations.map { it.description },
    lastWateredAt = lastWateredAt?:"Uknown",
    wateredStreak = wateredStreak,
    shadeLevel = shadeLevel?:""
)

fun UserPlantEntity.toDomain() = UserPlant(
    id = id,
    plantId = plantId,
    commonName = commonName,
    scientificName = scientificName,
    careLevel = careLevel,
    watering = watering,
    defaultImage = defaultImage,
    recommendations = recommendations.map { Recommendation(0,it,"") },
    wateredStreak = wateredStreak,
    lastWateredAt = lastWateredAt
)

