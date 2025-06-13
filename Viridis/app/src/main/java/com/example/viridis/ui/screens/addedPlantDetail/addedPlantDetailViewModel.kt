package com.example.viridis.ui.screens.addedPlantDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddedPlantDetailViewModel : ViewModel() {
    private val repository = PlantRepositoryImpl()

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant: StateFlow<Plant?> get() = _plant

    fun loadPlantById(plantId: String) {
        viewModelScope.launch {
            val allPlants = repository.getPlants()
            _plant.value = allPlants.find { it.id == plantId }
        }
    }

    fun deletePlant() {
        viewModelScope.launch {
            _plant.value?.let {
                val updatedList = repository.deletePlant(it)
                _plant.value = null
            }
        }
    }
}
