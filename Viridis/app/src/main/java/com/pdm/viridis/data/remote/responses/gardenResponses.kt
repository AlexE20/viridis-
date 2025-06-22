package com.pdm.viridis.data.remote.responses

//Not used
    data class GardenResponse(
        val id: String,
        val name: String,
        val idUser: String,
        val shadeLevel: String
    )

    data class GardenRequest(
        val name:String,
        val shadeLevel:String
    )