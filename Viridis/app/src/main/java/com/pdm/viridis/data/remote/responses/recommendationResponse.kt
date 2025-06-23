package com.pdm.viridis.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("description") val description: String?,
    @SerializedName("type") val type: String?
)
