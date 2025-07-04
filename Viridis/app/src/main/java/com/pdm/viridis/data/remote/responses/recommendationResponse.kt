package com.pdm.viridis.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @SerializedName("description") val description: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("type") val type: String?
)
