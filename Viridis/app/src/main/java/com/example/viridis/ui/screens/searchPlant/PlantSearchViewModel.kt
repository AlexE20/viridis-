package com.example.viridis.ui.screens.searchPlant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class plantSearchViewModel(): ViewModel() {
    private val repository= PlantRepositoryImpl()

    private val _searchText = MutableStateFlow("")
    val searchText : StateFlow<String> = _searchText

    private val _filteredPlants = MutableStateFlow<List<Plant>>(emptyList())
    val filteredPlants : StateFlow<List<Plant>> = _filteredPlants

    private var allPLants : List<Plant> = emptyList()

    init {
        loadPlants()
    }

    private fun loadPlants(){
        viewModelScope.launch {
            allPLants = repository.getPlants()
            _filteredPlants.value = allPLants
        }
    }

    fun onSearchTextChange(newText : String){
        _searchText.value = newText

        _filteredPlants.value = if (newText.isBlank()) {
            allPLants
        } else {
            allPLants.filter { plant ->
                plant.name.contains(newText, ignoreCase = true)
            }
        }
    }
}