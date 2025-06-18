package com.example.viridis.Retrofit

import com.example.viridis.data.model.Garden

data class GardenResponse(
    val id: String,
    val idUser: String,
    val name: String,
    val shadeLevel: String
)

fun GardenResponse.toDomain(): Garden{
    return Garden(
        id = id,
        user = idUser,
        name = name,
        shade = shadeLevel
    )
}