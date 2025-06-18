package com.example.viridis.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Dao
import com.example.viridis.ViridisApplication
import com.example.viridis.data.local.GardenDao
import com.example.viridis.data.model.Garden
import com.example.viridis.data.repository.GardenRepository
import com.example.viridis.data.repository.GardenRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GardenViewModel(
    private val repository: GardenRepository,
    
) : ViewModel() {


    private val _gardens = MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>>
        get() = _gardens

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun fetchGardens() {
        viewModelScope.launch {
            val data=repository.getGardens()
            println(data)
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _gardens.value = repository.getGardens()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar jardines: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val repository = app.appProvider.provideGardenRepository()
                GardenViewModel(repository)
            }
        }
    }
}


