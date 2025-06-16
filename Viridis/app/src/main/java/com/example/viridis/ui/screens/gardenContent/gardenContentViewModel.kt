package com.example.viridis.ui.screens.gardenContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.AppProvider
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GardenContentViewModel(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val gardenId = savedStateHandle.get<Int>("gardenId") ?: 0
    private val gardenName = savedStateHandle.get<String>("gardenName") ?: ""

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants

    init {
        viewModelScope.launch {
            plantRepository.getPlantsByGardenFlow(gardenId).collectLatest {
                _plants.value = it
            }
        }
    }

    fun getGardenName(): String = gardenName

    companion object {
        fun provideFactory(
            appProvider: AppProvider
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = this.createSavedStateHandle()
                GardenContentViewModel(
                    appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}
