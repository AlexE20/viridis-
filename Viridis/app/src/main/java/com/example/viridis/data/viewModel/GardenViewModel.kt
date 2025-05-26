package com.example.viridis.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viridis.data.model.Garden
import com.example.viridis.data.repository.GardenRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GardenViewModel(

): ViewModel(){
    private val repository= GardenRepositoryImpl()

    private val _gardens= MutableStateFlow<List<Garden>>(emptyList())
    val gardens: StateFlow<List<Garden>>
        get()=_gardens

    init {
        loadGardens()
    }

    private fun loadGardens(){
        viewModelScope.launch {
            val gardens=repository.getGardens()
            _gardens.value= gardens
        }
    }
}