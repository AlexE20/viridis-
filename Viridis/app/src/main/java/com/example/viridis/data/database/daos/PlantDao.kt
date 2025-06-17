package com.example.viridis.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viridis.data.database.entities.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlant(plant: PlantEntity)

    @Query("SELECT * FROM Plant WHERE gardenId = :gardenId")
    fun getPlantsByGarden(gardenId: Int): Flow<List<PlantEntity>>

    @Delete
    suspend fun deletePlant(plant: PlantEntity)

    @Query("SELECT * FROM Plant")
    fun getAllPlants(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM Plant")
    suspend fun getAllPlantsOnce() : List<PlantEntity>


////////////
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addPlants(plants: List<PlantEntity>)

}