package com.pdm.viridis.ui.screens.gardenContent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepository
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.lifecycle.createSavedStateHandle
import com.pdm.viridis.data.repository.Favorite.FavoriteRepository

class GardenContentViewModel(
    private val userPlantRepository: UserPlantRepository,
    private val authRepository: AuthRepository,
    private val gardenRepository: GardenRepository,
    private val favoriteRepository : FavoriteRepository,
    private val onFavoriteChanged: () -> Unit = {}
) : ViewModel() {
    private val _plants = MutableStateFlow<List<UserPlant>>(emptyList())
    val plants: StateFlow<List<UserPlant>> get() = _plants

    private val _showSuccessSheet = MutableStateFlow(false)
    val showSuccessSheet: StateFlow<Boolean> = _showSuccessSheet

    private val _showDeleteConfirmation = MutableStateFlow(false)
    val showDeleteConfirmation: StateFlow<Boolean> = _showDeleteConfirmation

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val _showConnectionError = MutableStateFlow(false)
    val showConnectionError: StateFlow<Boolean> = _showConnectionError

    fun setConnectedState(state: Boolean) {
        _isConnected.value = state
    }


    //private val _pendingGardenToDelete = MutableStateFlow<String?>(null)

    fun deleteGarden(gardenId: String){
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            gardenRepository.deleteGarden(userId,gardenId)
            _showSuccessSheet.value = true
            _showDeleteConfirmation.value = false
        }

    }

    fun listenPlants(gardenId: String, isConnected: Boolean) {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            val result = if (isConnected) {
                try {
                    val remotePlants = userPlantRepository.getPlants(userId, gardenId)
                    userPlantRepository.saveLocalPlants(userId, gardenId)
                    remotePlants
                } catch (e: Exception) {
                    e.printStackTrace()
                    userPlantRepository.getLocalPlants(gardenId).first() // fallback offline
                }
            } else {
                userPlantRepository.getLocalPlants(gardenId).first()
            }

            _plants.value = result
        }
    }

    fun dismissSuccessSheet(){
        _showSuccessSheet.value = false
    }

    fun showDeleteConfirmation() {
        _showDeleteConfirmation.value = true
    }

    fun cancelDelete() {
        _showDeleteConfirmation.value = false
        //_pendingGardenToDelete.value = null
    }

    fun showConnectionError() {
        _showConnectionError.value = true
    }

    fun dismissConnectionError() {
        _showConnectionError.value = false
    }

    fun toggleFavorite(gardenId: String) {
        viewModelScope.launch {
            val currentState = favoriteRepository.isFavorite(gardenId)
            val newState = !currentState
            favoriteRepository.setFavorite(gardenId, newState)
            _isFavorite.value = newState
            onFavoriteChanged?.invoke()
        }
    }

    suspend fun checkInitialFavoriteState(gardenId: String) {
        _isFavorite.value = favoriteRepository.isFavorite(gardenId)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val plantRepository = app.appProvider.provideUserPlantRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                val gardenRepository =  app.appProvider.provideGardenRepository()
                val favoriteRepository = app.appProvider.provideFavoriteRepository()
                GardenContentViewModel(plantRepository, authRepository, gardenRepository, favoriteRepository)
            }
        }
    }
}