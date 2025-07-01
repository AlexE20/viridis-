package com.pdm.viridis.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.pdm.viridis.data.database.AppDatabase
import com.pdm.viridis.data.remote.RetrofitInstance
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Auth.AuthRepositoryImpl
import com.pdm.viridis.data.repository.Favorite.FavoriteRepository
import com.pdm.viridis.data.repository.Favorite.FavoriteRepositoryImp
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.repository.Garden.GardenRepositoryImpl
import com.pdm.viridis.data.repository.Plant.PlantRepository
import com.pdm.viridis.data.repository.Plant.PlantRepositoryImpl
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepository
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepositoryImpl

private const val USER_PREFERENCE_NAME = "user_preferences"

class AppProvider(
    context: Context,
    private val dataStore: DataStore<Preferences>
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

    private val appDatabase = AppDatabase.getDatabase(context)
    
    private val authService = RetrofitInstance.authService

    private val gardenService = RetrofitInstance.gardenService

    private val userPlantService = RetrofitInstance.userPlantService

    private val plantService = RetrofitInstance.plantService
    
    private val userService = RetrofitInstance.userService

    private val authRepository: AuthRepository = AuthRepositoryImpl(
        authService = authService,
        dataStore = context.dataStore
    )

    private val userPlantRepository: UserPlantRepository = UserPlantRepositoryImpl(
        dao = appDatabase.plantDao(),
        userPlantService
    )

    private val plantRepository: PlantRepository = PlantRepositoryImpl(
        plantService
    )
    private val favoriteRepository : FavoriteRepository = FavoriteRepositoryImp(
        appDatabase.favoriteDao()
    )

    fun provideGardenRepository(): GardenRepository {
        return GardenRepositoryImpl(appDatabase.gardenDao(), gardenService)
    }

    fun provideAuthRepository(): AuthRepository = authRepository

    fun provideUserPlantRepository(): UserPlantRepository = userPlantRepository

    fun providePlantRepository(): PlantRepository = plantRepository

    fun provideFavoriteRepository() : FavoriteRepository = favoriteRepository
}

