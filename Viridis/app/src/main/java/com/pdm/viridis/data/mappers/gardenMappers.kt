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