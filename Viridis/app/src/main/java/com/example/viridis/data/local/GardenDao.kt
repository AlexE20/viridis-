package com.example.viridis.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {
    @Query("SELECT * FROM Garden")
    fun getAllGardens(): Flow<List<GardenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGarden(garden: GardenEntity)

    @Delete
    suspend fun deleteGarden(garden: GardenEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGardens(gardens: List<GardenEntity>)
}