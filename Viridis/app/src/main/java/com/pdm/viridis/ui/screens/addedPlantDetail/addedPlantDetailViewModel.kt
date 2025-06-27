package com.pdm.viridis.ui.screens.addedPlantDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.ui.screens.gardenContent.GardenContentViewModel
import com.pdm.viridis.ui.screens.plantContent.PlantContentViewModel


class AddedPlantDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    companion object {
        fun Factory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val savedStateHandle = createSavedStateHandle()
                AddedPlantDetailViewModel(
                    savedStateHandle
                )
            }
        }
    }
}


