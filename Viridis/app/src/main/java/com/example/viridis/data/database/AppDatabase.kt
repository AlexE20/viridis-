package com.example.viridis.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viridis.data.database.daos.GardenDao
import com.example.viridis.data.database.daos.PlantDao
import com.example.viridis.data.database.entities.GardenEntity
import com.example.viridis.data.database.entities.PlantEntity

@Database(
    entities = [GardenEntity::class, PlantEntity::class],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun gardenDao(): GardenDao
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "viridis_database"
                ).build()
                    .also{INSTANCE = it}
                instance
            }
        }
    }
}