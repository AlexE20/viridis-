package com.pdm.viridis.ui.screens.searchPlant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.repository.Plant.PlantRepository
import com.pdm.viridis.data.repository.Plant.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantSearchViewModel(
    private val plantRepository: PlantRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchStarted = MutableStateFlow(false)
    val searchStarted: StateFlow<Boolean> = _searchStarted

    fun onSearchStarted() {
        _searchStarted.value = true
    }


    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }

    fun searchPlants() {
        val query = _searchText.value.trim()
        if (query.isBlank()) return

        viewModelScope.launch {
            _isLoading.value = true
            _plants.value = try {
                plantRepository.getCatalogPlantsByName(query)
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val plantRepository = app.appProvider.providePlantRepository()
                PlantSearchViewModel(plantRepository)
            }
        }
    }
}