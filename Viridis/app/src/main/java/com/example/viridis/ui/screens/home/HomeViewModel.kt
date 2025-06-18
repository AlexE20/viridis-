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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    init {
        loadGardens()
    }

    private fun loadGardens() {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            if (isConnected()) {
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
