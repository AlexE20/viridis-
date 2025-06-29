package com.pdm.viridis.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "favorite_gardens", primaryKeys = ["gardenId"])
class FavoriteEntity(
    @ColumnInfo(name = "gardenId") val gardenId: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
)