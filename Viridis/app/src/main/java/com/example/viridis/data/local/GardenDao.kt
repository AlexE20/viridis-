package com.example.viridis.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {
    @Query("SELECT * FROM garden")
    fun getAllGardens(): Flow<List<GardenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGarden(garden: GardenEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGardens(gardens: List<GardenEntity>)

    @Delete
    suspend fun deleteGarden(garden: GardenEntity)

    @Query("DELETE FROM garden WHERE id = :id")
    suspend fun deleteGardenById(id: String)

    @Query("DELETE FROM garden")
    suspend fun clearAllGardens()
}