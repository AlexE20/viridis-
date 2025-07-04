package com.pdm.viridis.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.viridis.data.database.entities.UserPlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlant(plant: UserPlantEntity)

    @Query("SELECT * FROM user_plants WHERE gardenId = :gardenId")
    fun getPlantsByGarden(gardenId: String): Flow<List<UserPlantEntity>>

    @Query("DELETE FROM user_plants WHERE id = :userPlantId")
    suspend fun delete(userPlantId: String)

    @Query("SELECT * FROM user_plants")
    fun getAllPlants(): Flow<List<UserPlantEntity>>

    @Query("SELECT * FROM user_plants")
    suspend fun getAllPlantsOnce(): List<UserPlantEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlants(plants: List<UserPlantEntity>)

    @Query("DELETE FROM user_plants WHERE gardenId = :gardenId")
    suspend fun deletePlantsFromGarden(gardenId: String)

}