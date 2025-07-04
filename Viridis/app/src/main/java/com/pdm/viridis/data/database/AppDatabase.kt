package com.pdm.viridis.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pdm.viridis.data.database.daos.FavoriteDao
import com.pdm.viridis.data.database.daos.GardenDao
import com.pdm.viridis.data.database.daos.PlantDao
import com.pdm.viridis.data.database.entities.FavoriteEntity
import com.pdm.viridis.data.database.entities.GardenEntity
import com.pdm.viridis.data.database.entities.UserPlantEntity
import com.pdm.viridis.utils.Converters

@Database(
    entities = [GardenEntity::class, UserPlantEntity::class, FavoriteEntity::class],
    version = 6,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gardenDao(): GardenDao
    abstract fun plantDao(): PlantDao
    abstract fun favoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "viridis_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{INSTANCE = it}
                instance
            }
        }
    }
}