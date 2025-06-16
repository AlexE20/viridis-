package com.example.viridis.data.mappers

import com.example.viridis.data.model.Garden
import com.example.viridis.data.database.entities.GardenEntity
import com.example.viridis.data.model.Plant
import com.example.viridis.data.database.entities.PlantEntity

fun Garden.toEntity(): GardenEntity {
    return GardenEntity(
        id = id,
        idUser = user,
        name = name,
        shadeLevel = shade
    )
}

fun GardenEntity.toModel(): Garden {
    return Garden(
        id = id,
        user = idUser,
        name = name,
        shade = shadeLevel
    )
}

fun Plant.toEntity(): PlantEntity {
    return PlantEntity(
        id = 0,
        gardenId = idGarden,
        plantId = id,
        commonName = name,
        scientificName = listOf(scientificName),
        careLevel = difficulty,
        watering = watering,
        defaultImage = imageUrl,
        recommendations = recommendations
    )
}

fun PlantEntity.toModel(): Plant {
    return Plant(
        id = plantId,
        name = commonName,
        scientificName = scientificName.firstOrNull() ?: "",
        watering = watering,
        recommendations = recommendations,
        imageUrl = defaultImage,
        streak = 0,
        lastWatered = "",
        idGarden = gardenId,
        difficulty = careLevel,
        shadeLevel = ""
    )
}