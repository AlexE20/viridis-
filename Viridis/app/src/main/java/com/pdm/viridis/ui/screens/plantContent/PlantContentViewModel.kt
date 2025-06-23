package com.pdm.viridis.ui.screens.plantContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.data.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.createSavedStateHandle

class PlantContentViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _plant = MutableStateFlow<Plant?>(savedStateHandle.get<Plant>("plant"))
    val plant: StateFlow<Plant?> = _plant

    init {
        _plant.value = savedStateHandle.get<Plant>("plant")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                PlantContentViewModel(savedStateHandle)
            }
        }
    }
}

