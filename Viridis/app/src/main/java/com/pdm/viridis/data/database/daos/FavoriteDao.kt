package com.pdm.viridis.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.viridis.data.database.entities.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite_gardens WHERE gardenId = :gardenId")
    suspend fun getFavorite(gardenId: String): FavoriteEntity?

    @Query("SELECT * FROM favorite_gardens")
    suspend fun getAllFavorites(): List<FavoriteEntity>
}