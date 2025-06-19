package com.example.viridis.ui.screens.plantContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepository
import com.example.viridis.data.repository.plantRepository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantContentViewModel(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val plantId = savedStateHandle.get<String>("plantId") ?: ""

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant : StateFlow<Plant?> get() = _plant

    init {
        viewModelScope.launch {
            plantRepository.getPlantsFlow().collect { plantList ->
                _plant.value = plantList.find { it.id == plantId }
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                val savedStateHandle = createSavedStateHandle()
                PlantContentViewModel(
                    app.appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}