package com.pdm.viridis.ui.screens.addedPlantDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepository
import com.pdm.viridis.ui.screens.gardenContent.GardenContentViewModel
import com.pdm.viridis.ui.screens.plantContent.PlantContentViewModel
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class AddedPlantDetailViewModel(
    private val authRepository: AuthRepository,
    private val userPlantRepository: UserPlantRepository,
    ) : ViewModel() {

    private val _showSuccessSheet = MutableStateFlow(false)
    val showSuccessSheet: StateFlow<Boolean> = _showSuccessSheet

    private val _showDeleteConfirmation = MutableStateFlow(false)
    val showDeleteConfirmation: StateFlow<Boolean> = _showDeleteConfirmation

    private val _pendingGardenToDelete = MutableStateFlow<String?>(null)


    fun deletePlant(userPlantId: String){
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            userPlantRepository.deletePlant(userId,userPlantId)
            _showSuccessSheet.value = true
            _showDeleteConfirmation.value = false
        }

    }

    fun dismissSuccessSheet(){
        _showSuccessSheet.value = false
    }

    fun showDeleteConfirmation() {
        _showDeleteConfirmation.value = true
    }

    fun cancelDelete() {
        _showDeleteConfirmation.value = false
        _pendingGardenToDelete.value = null
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val authRepository = app.appProvider.provideAuthRepository()
                val plantRepository = app.appProvider.provideUserPlantRepository()
                AddedPlantDetailViewModel(authRepository,plantRepository)
            }
        }
    }
}


