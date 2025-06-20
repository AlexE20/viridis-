package com.pdm.viridis.ui.screens.gardenContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.UserPlant
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Plant.UserPlantRepository
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class GardenContentViewModel(
    private val userPlantRepository: UserPlantRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _plants = MutableStateFlow<List<UserPlant>>(emptyList())
    val plants: StateFlow<List<UserPlant>> get() = _plants


    fun loadPlants(gardenId: String) {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            val result = userPlantRepository.getPlants(userId, gardenId)
            _plants.value = result
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val plantRepository = app.appProvider.provideUserPlantRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                GardenContentViewModel(plantRepository, authRepository)
            }
        }
    }
}