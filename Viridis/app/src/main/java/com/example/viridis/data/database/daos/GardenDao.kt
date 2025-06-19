package com.example.viridis.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viridis.data.database.entities.GardenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {
    @Query("SELECT * FROM Garden")
    fun getAllGardens(): Flow<List<GardenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGarden(garden: GardenEntity)

    @Query("SELECT * FROM Garden WHERE idUser = :userId")
    suspend fun getGardensByUser(userId: String): List<GardenEntity>

    @Delete
    suspend fun deleteGarden(garden: GardenEntity)

//////////

//    @Query("SELECT * FROM Garden")
//    fun getAllGardens(): Flow<List<GardenEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addGardens(gardens: List<GardenEntity>)
}