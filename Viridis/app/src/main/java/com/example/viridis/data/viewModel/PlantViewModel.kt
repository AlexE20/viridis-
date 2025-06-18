package com.example.viridis.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viridis.data.model.Plant
import com.example.viridis.data.repository.PlantRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantViewModel(): ViewModel() {
    private val repository= PlantRepositoryImpl()

    private val _plants= MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>>
        get()=_plants


  
}