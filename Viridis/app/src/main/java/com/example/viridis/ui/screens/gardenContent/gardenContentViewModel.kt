package com.example.viridis.ui.screens.gardenContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.AppProvider
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepository
import com.example.viridis.ui.screens.home.HomeViewModel
import com.example.viridis.data.repository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.viridis.data.mappers.toModel

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
            plantRepository.getPlantsByGarden(gardenId).collectLatest { plantEntities ->
                _plants.value = plantEntities.map { it.toModel() }
            }
        }
    }

    fun getGardenName(): String = gardenName

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as ViridisApplication
                val savedStateHandle = createSavedStateHandle()
                GardenContentViewModel(
                    aplication.appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}
