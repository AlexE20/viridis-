package com.pdm.viridis.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Favorite.FavoriteRepository
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepository
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val authRepository: AuthRepository,
    private val userPlantRepository: UserPlantRepository,
    private val favoriteRepository : FavoriteRepository
    ) : ViewModel() {


    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    private val _imageUrlsMap = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val imageUrlsMap: StateFlow<Map<String, List<String>>> = _imageUrlsMap
    private val _favoriteStates = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favoriteStates: StateFlow<Map<String, Boolean>> = _favoriteStates

    private val _refreshFavorites = MutableStateFlow(0)
    private val loadedGardenIds = mutableSetOf<String>()

    fun loadGardens(isConnected: Boolean) {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            if (isConnected) {
                try {
                    val remoteGardens = gardenRepository.getGardens(userId)
                    gardenRepository.saveLocalGardens(userId)

                    remoteGardens.forEach { garden ->
                        userPlantRepository.saveLocalPlants(userId, garden.id)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            gardenRepository.getLocalGardens()
                .distinctUntilChanged()
                .collect { list ->
                    _gardens.value = list
                    _favoriteStates.value = list.associate {
                        garden -> garden.id to favoriteRepository.isFavorite(garden.id)
                    }
                    fetchImageUrlsForGardens(list, userId, isConnected)
                }
        }
    }


    private fun fetchImageUrlsForGardens(gardens: List<Garden>, userId: String, isConnected: Boolean) {
        gardens.forEach { garden ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val plants = if (isConnected) {
                        userPlantRepository.getPlants(userId, garden.id)
                    } else {
                        userPlantRepository.getLocalPlants(garden.id).first()
                    }

                    val urls = plants.map { it.defaultImage }.take(4)
                    val currentUrls = _imageUrlsMap.value[garden.id]

                    if (currentUrls != urls) {
                        _imageUrlsMap.update { it + (garden.id to urls) }
                    }

                    loadedGardenIds.add(garden.id)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    //just in case is necessary
    suspend fun loadFavoriteStates(gardenIds: List<String>) {
        _favoriteStates.value = gardenIds.associateWith { gardenId ->
            favoriteRepository.isFavorite(gardenId)
        }
    }

    fun refreshFavoriteStates() {
        _refreshFavorites.value++
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val gardenRepository = app.appProvider.provideGardenRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                val plantRepository = app.appProvider.provideUserPlantRepository()
                val favoriteRepository = app.appProvider.provideFavoriteRepository()
                HomeViewModel(gardenRepository, authRepository, plantRepository, favoriteRepository)
            }
        }
    }
}