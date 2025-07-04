package com.pdm.viridis.ui.screens.gardenCreation.gardenName

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.repository.Garden.GardenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GardenNameViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _gNameText = MutableStateFlow(savedStateHandle.get<String>("gardenName") ?: "")
    val gNameText : StateFlow<String> = _gNameText

    fun onNameChanged(newName: String) {
        _gNameText.value = newName
        savedStateHandle["gardenName"] = newName
    }

    fun isValid(): Boolean {
        return _gNameText.value.trim().isNotEmpty()
    }

    fun getGardenName(): String = _gNameText.value.trim()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                GardenNameViewModel(
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    }
}