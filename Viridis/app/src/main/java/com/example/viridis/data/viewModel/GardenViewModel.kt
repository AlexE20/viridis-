package com.example.viridis.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viridis.data.model.Garden
import com.example.viridis.data.repository.GardenRepository
import com.example.viridis.data.repository.GardenRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GardenViewModel(
    private val repository: GardenRepository
) : ViewModel() {


    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>>
        get() = _gardens

    init {
        loadGardens()
    }

    private fun loadGardens() {
        viewModelScope.launch {
            if (isConnected()) {

                repository.saveLocalGardens()
                repository.getLocalGardens().collect { list ->
                    _gardens.value = list
                }
            } else {

                val dummy = repository.getGardens()
                _gardens.value = dummy
            }
        }
    }

    private fun isConnected(): Boolean {
        return true // o false para probar offline
    }
}


