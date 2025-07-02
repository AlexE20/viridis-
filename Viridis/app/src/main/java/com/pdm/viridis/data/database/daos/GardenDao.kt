package com.pdm.viridis.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.viridis.data.database.entities.GardenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {
    @Query("SELECT * FROM Garden")
    fun getAllGardens(): Flow<List<GardenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGarden(garden: GardenEntity)

    @Query("SELECT * FROM Garden WHERE idUser = :userId")
    fun getGardensByUser(userId: String): Flow<List<GardenEntity>>

    @Query("DELETE FROM Garden WHERE id = :gardenId")
    suspend fun deleteGarden(gardenId: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGardens(gardens: List<GardenEntity>)
}