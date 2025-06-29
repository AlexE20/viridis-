package com.pdm.viridis.data.repository.Favorite

interface FavoriteRepository {
    suspend fun setFavorite(gardenId: String, isFavorite: Boolean)
    suspend fun isFavorite(gardenId: String): Boolean
    suspend fun getAllFavorites(): Map<String, Boolean>
}