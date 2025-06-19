package com.example.viridis.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.model.Garden
import com.example.viridis.data.repository.Auth.AuthRepository
import com.example.viridis.data.repository.Garden.GardenRepository
import com.example.viridis.utils.extractUidFromToken
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants

    init {
        loadGardens()
    }

    private fun loadGardens() {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            if (isConnected()) {
                gardenRepository.getGardens(userId)
                gardenRepository.saveLocalGardens(userId)
                gardenRepository.getLocalGardens().collect { list ->
                    _gardens.value = list
                }
            } else {
                val offlineList = gardenRepository.getGardens(userId)
                _gardens.value = offlineList
            }
        }
    }

    private fun isConnected(): Boolean {
        // Replace with real connectivity check
        return true
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val gardenRepository = app.appProvider.provideGardenRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                HomeViewModel(gardenRepository, authRepository)
            }
        }
    }
}
