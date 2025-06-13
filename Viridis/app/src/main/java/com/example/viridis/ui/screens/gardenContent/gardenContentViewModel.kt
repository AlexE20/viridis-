package com.example.viridis.ui.screens.gardenContent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.plantRepository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class gardenContentViewModel(): ViewModel() {
    private val repository= PlantRepositoryImpl()

    private val _plants= MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>>
        get()=_plants


    fun loadPlantsByGarden(gardenId:Int){
        viewModelScope.launch{
        val filteredPlants=repository.getPlantsByGarden(gardenId)
        _plants.value=filteredPlants
        }
    }
}