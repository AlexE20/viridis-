package com.pdm.viridis.ui.screens.plantContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.repository.Plant.PlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantContentViewModel(
    private val plantRepository: PlantRepository,
) : ViewModel(){
	
	private val _plants=MutableStateFlow<List<Plant>>(emptyList())
	val plants : StateFlow<List<Plant>> get() = _plants
	
	fun loaPlantsByName(plantName: String) {
		viewModelScope.launch {
			val plants=plantRepository.getCatalogPlantsByName(plantName)
			_plants.value=plants
		}
	}
	

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                PlantContentViewModel(
                    app.appProvider.providePlantRepository(),
                    
                )
            }
        }
    }
}
