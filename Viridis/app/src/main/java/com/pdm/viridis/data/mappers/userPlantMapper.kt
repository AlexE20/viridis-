package com.pdm.viridis.data.mappers

import com.pdm.viridis.data.database.entities.UserPlantEntity
import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.remote.responses.UserPlantResponse
import java.util.UUID

fun UserPlantResponse.toDomain() = UserPlant(
    id = id,
    gardenId = gardenId,
    plantId = plantId,
    commonName = commonName,
    scientificName = scientificName,
    careLevel = careLevel,
    watering = watering,
    defaultImage = defaultImage,
    recommendations = recommendations.map { it.description.orEmpty() },
    wateredStreak = wateredStreak,
    lastWateredAt = lastWateredAt
)

fun UserPlant.toEntity() = UserPlantEntity(
    id = id ?: "",
    gardenId = gardenId,
    plantId = plantId,
    commonName = commonName,
    scientificName = scientificName,
    careLevel = careLevel,
    watering = watering,
    defaultImage = defaultImage,
    recommendations = recommendations,
    wateredStreak = wateredStreak,
    lastWateredAt = lastWateredAt
)

fun UserPlantEntity.toDomain() = UserPlant(
    id = id,
    gardenId = gardenId,
    plantId = plantId,
    commonName = commonName,
    scientificName = scientificName,
    careLevel = careLevel,
    watering = watering,
    defaultImage = defaultImage,
    recommendations = recommendations,
    wateredStreak = wateredStreak,
    lastWateredAt = lastWateredAt
)

