package com.pdm.viridis.data.mappers

import com.pdm.viridis.data.database.entities.UserPlantEntity
import com.pdm.viridis.data.model.UserPlant
import java.util.UUID

fun UserPlantEntity.toModel() = UserPlant(
    id            = id,
    plant_id      = plantId,
    common_name   = commonName,
    scientific_name = scientificName,
    care_level    = careLevel,
    watering      = watering,
    default_image = defaultImage,
    recommendations = recommendations
)

fun UserPlant.toEntity(gardenId: String) = UserPlantEntity(
    id = id ?: UUID.randomUUID().toString(),
    gardenId = gardenId,
    plantId = plant_id ?: "",
    commonName = common_name ?: "",
    scientificName = scientific_name ?: emptyList(),
    careLevel = care_level ?: "",
    watering = watering ?: "",
    defaultImage = default_image ?: "",
    recommendations = recommendations ?: emptyList()
)