package com.example.viridis.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gardenRepository: GardenRepository,
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>> get() = _gardens

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants

    init {
        loadGardens()
        loadPlants()
    }

    private fun loadGardens() {
        viewModelScope.launch {
            gardenRepository.getLocalGardens().collectLatest { list ->
                _gardens.value = list
            }
        }
    }

    private fun loadPlants() {
        viewModelScope.launch {
            plantRepository.getPlantsFlow().collectLatest { list ->
                _plants.value = list
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                val gardenRepo = app.appProvider.provideGardenRepository()
                val plantRepo = app.appProvider.providePlantRepository()
                HomeViewModel(gardenRepo, plantRepo)
            }
        }
    }
}
