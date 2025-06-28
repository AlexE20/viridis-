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

class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val authRepository: AuthRepository,
    private val userPlantRepository: UserPlantRepository,
    ) : ViewModel() {

    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    private val _imageUrlsMap = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val imageUrlsMap: StateFlow<Map<String, List<String>>> = _imageUrlsMap

    private val loadedGardenIds = mutableSetOf<String>()

    fun loadGardens() {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            if (isConnected()) {
                gardenRepository.getGardens(userId)
                gardenRepository.saveLocalGardens(userId)
                /*gardenRepository.getLocalGardens().collect { list ->
                    _gardens.value = list
                    fetchImageUrlsForGardens(list, userId)
                }*/
                gardenRepository.getLocalGardens()
                    .distinctUntilChanged()
                    .collect { list ->
                        _gardens.value = list
                        fetchImageUrlsForGardens(list, userId)
                    }
            } else {
                val offlineList = gardenRepository.getGardens(userId)
                _gardens.value = offlineList
                fetchImageUrlsForGardens(offlineList, userId)
            }
        }
    }

    fun fetchImageUrlsForGardens(gardens: List<Garden>, userId: String) {
        gardens.forEach { garden ->
            //if (loadedGardenIds.contains(garden.id)) return@forEach

            viewModelScope.launch(Dispatchers.IO) {
                val plants = userPlantRepository.getPlants(userId, garden.id)
                val urls = plants.map { it.defaultImage }.take(4)
                val currentUrls = _imageUrlsMap.value[garden.id]

                if (currentUrls != urls) {
                    _imageUrlsMap.update { it + (garden.id to urls) }
                }

                //_imageUrlsMap.update { it + (garden.id to urls) }
                loadedGardenIds.add(garden.id)
            }
        }
    }

    fun isConnected(): Boolean {
        // Replace with real connectivity check
        return true
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val gardenRepository = app.appProvider.provideGardenRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                val plantRepository = app.appProvider.provideUserPlantRepository()
                HomeViewModel(gardenRepository, authRepository, plantRepository)
            }
        }
    }
}