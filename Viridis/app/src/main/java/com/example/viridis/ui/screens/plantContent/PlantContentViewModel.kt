package com.example.viridis.ui.screens.plantContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantContentViewModel : ViewModel(){
    private val repository = PlantRepositoryImpl()

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant : StateFlow<Plant?> = _plant

    fun loadPlantById(plantId : String){
        viewModelScope.launch {
            val allPlants = repository.getPlants()
            _plant.value = allPlants.find { it.id == plantId } //WTF why is it a String in the Entity?????
        }
    }
}