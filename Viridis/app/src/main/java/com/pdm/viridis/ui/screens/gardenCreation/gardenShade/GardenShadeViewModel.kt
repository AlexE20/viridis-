package com.pdm.viridis.ui.screens.gardenCreation.gardenShade

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.local.GardenEntity
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.model.Garden
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GardenShadeViewModel(
    private val repository: GardenRepository,
    private val savedStateHandle: SavedStateHandle,
    private val userId : String,
    private val gardenName : String
) : ViewModel() {

    val shadeOptions: Map<String, String> = linkedMapOf(
        "Full shade" to "full_shade",
        "Part shade" to "part_shade",
        "Sun part shade" to "sun-part_shade",
        "Full sun" to "full_sun"
    )

    private val _selectedShade = MutableStateFlow("")
    val selectedShade : StateFlow<String> = _selectedShade.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving : StateFlow<Boolean> = _isSaving

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun onShadeSelected(shade: String) {
        _selectedShade.value = shade
    }

    fun isValid(): Boolean = _selectedShade.value.isNotBlank()

    fun saveGarden(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!isValid()) return@launch

        _isSaving.value = true
        _error.value = null

        val garden = Garden(
            id = "",
            name = gardenName,
            idUser = userId,
            shadeLevel = _selectedShade.value
        )

        runCatching {
            repository.addGarden(userId, garden)
        }.onSuccess {
            onSuccess()
        }.onFailure { throwable ->
            _error.value = throwable.localizedMessage ?: "Unknown error"
        }

        _isSaving.value = false
    }

    companion object {
        fun factory(app: ViridisApplication, gardenName: String, userId: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    GardenShadeViewModel(
                        repository = app.appProvider.provideGardenRepository(),
                        savedStateHandle = createSavedStateHandle(),
                        userId = userId, gardenName = gardenName
                    )
                }
            }

    }
}