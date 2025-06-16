package com.example.viridis.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.model.Garden
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.gardenRepository.GardenRepository
import com.example.viridis.data.repository.plantRepository.PlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants
    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    init {
        loadGardens()
        loadPlants()
    }

    private fun loadPlants() {
        viewModelScope.launch {
            _plants.value = plantRepository.getPlants()
        }
    }

    private fun loadGardens() {
        viewModelScope.launch {
            if (isConnected()) {

                gardenRepository.saveLocalGardens()
                gardenRepository.getLocalGardens().collect { list ->
                    _gardens.value = list
                }
            } else {

                val dummy = gardenRepository.getGardens()
                _gardens.value = dummy
            }
        }
    }

    private fun isConnected(): Boolean {
        return true // o false para probar offline
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val gardenRepo = app.appProvider.provideGardenRepository()
                val plantRepo = app.appProvider.providePlantRepository()
                HomeViewModel(gardenRepo, plantRepo)
            }
        }
    }
}


