package com.pdm.viridis.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_gardens")
data class FavoriteEntity(
    @PrimaryKey val gardenId: String,
    val isFavorite: Boolean = false
)