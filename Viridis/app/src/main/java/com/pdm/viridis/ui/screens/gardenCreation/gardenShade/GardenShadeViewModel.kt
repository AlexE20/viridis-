package com.pdm.viridis.ui.screens.gardenCreation.gardenShade

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.remote.responses.GardenRequest
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GardenShadeViewModel(
    private val repository: GardenRepository,
    private val savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository
) : ViewModel() {

    val shadeOptions: Map<String, String> = linkedMapOf(
        "full_shade" to "Full shade",
        "part_shade" to "Part shade",
        "sun-part_shade" to "Sun part shade" ,
        "full_sun" to "Full sun"
    )


    private val _selectedShade = MutableStateFlow<String?>(null)
    val selectedShade: StateFlow<String?> = _selectedShade

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun onShadeSelected(shade: String) {
        _selectedShade.value = shade
    }

    fun isValid(): Boolean = _selectedShade.value != null

    fun saveGarden(onSuccess: () -> Unit = {}){
        viewModelScope.launch {
            _isSaving.value = true
            _error.value = null
            try {
                performSaveGarden()
                _isSaving.value = false
            } catch (e: Exception) {
                _isSaving.value = false
                _error.value = e.localizedMessage
            }
        }
    }

    suspend fun performSaveGarden() {
        val name = savedStateHandle.get<String>("gardenName")?.trim().orEmpty()
        val token = authRepository.token.first() ?: return
        val userId = extractUidFromToken(token) ?: return
        val shade = _selectedShade.value ?: return

        val request = GardenRequest(
            name = name,
            shadeLevel = shade
        )

        repository.addGarden(userId, request)

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)

                GardenShadeViewModel(
                    repository = app.appProvider.provideGardenRepository(),
                    savedStateHandle = createSavedStateHandle(),
                    authRepository = app.appProvider.provideAuthRepository()
                )
            }
        }
    }
}