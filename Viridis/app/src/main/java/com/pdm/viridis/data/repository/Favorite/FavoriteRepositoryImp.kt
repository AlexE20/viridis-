package com.pdm.viridis.data.repository.Favorite

import com.pdm.viridis.data.database.daos.FavoriteDao
import com.pdm.viridis.data.database.entities.FavoriteEntity

class FavoriteRepositoryImp(
    private val favoriteDao:  FavoriteDao
) : FavoriteRepository {
    override suspend fun setFavorite(gardenId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteDao.insertFavorite(FavoriteEntity(gardenId))
        } else {
            favoriteDao.removeFavorite(FavoriteEntity(gardenId))
        }
    }

    override suspend fun isFavorite(gardenId: String): Boolean {
        return favoriteDao.getFavorite(gardenId) != null
    }

    override suspend fun getAllFavorites(): Map<String, Boolean> {
        return favoriteDao.getAllFavorites()
            .associate { it.gardenId to true } // Only gardens that are favorite are in the table, just in case, you know, yeah... keep it up
    }
}