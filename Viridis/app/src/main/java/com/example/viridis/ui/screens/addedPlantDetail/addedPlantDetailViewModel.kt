package com.example.viridis.ui.screens.addedPlantDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.AppProvider
import com.example.viridis.data.repository.plantRepository.PlantRepository
import com.example.viridis.ui.screens.gardenContent.GardenContentViewModel


/*
class AddedPlantDetailViewModel(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val plantId = savedStateHandle.get<String>("plantId") ?: ""

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant: StateFlow<Plant?> get() = _plant

    init {
        viewModelScope.launch {
            plantRepository.getPlantsFlow().collect { plantList ->
                _plant.value = plantList.find { it.id == plantId }
            }
        }
    }

//    fun deletePlant() {
//        viewModelScope.launch {
//            _plant.value?.let {
//                plantRepository.deletePlant(it)
//                _plant.value = null
//            }
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as ViridisApplication
                val savedStateHandle = createSavedStateHandle()
                AddedPlantDetailViewModel(
                    aplication.appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}
*/

