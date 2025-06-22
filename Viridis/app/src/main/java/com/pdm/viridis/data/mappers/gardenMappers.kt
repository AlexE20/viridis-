package com.pdm.viridis.data.mappers

import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.database.entities.GardenEntity

fun Garden.toEntity(): GardenEntity {
    return GardenEntity(
        id = id,
        idUser = idUser,
        name = name,
        shadeLevel = shadeLevel
    )
}

fun GardenEntity.toModel(): Garden {
    return Garden(
        id = id,
        idUser = idUser,
        name = name,
        shadeLevel = shadeLevel
    )
}



//
//fun Plant.toEntity(): PlantEntity {
//    return PlantEntity(
//        id = 0,
//        gardenId = idGarden,
//        plantId = id,
//        commonName = name,
//        scientificName = listOf(scientificName),
//        careLevel = difficulty,
//        watering = watering,
//        defaultImage = imageUrl,
//        recommendations = recommendations
//    )
//}
//
//    fun PlantEntity.toModel(): Plant {
//    return Plant(
//        id = plantId,
//        name = commonName,
//        scientificName = scientificName.firstOrNull() ?: "",
//        watering = watering,
//        recommendations = recommendations,
//        imageUrl = defaultImage,
//        careLevel = "",
//        shadeLevel = ""
//    )
//}