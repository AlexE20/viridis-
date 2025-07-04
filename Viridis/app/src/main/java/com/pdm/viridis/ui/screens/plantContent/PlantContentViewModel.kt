package com.pdm.viridis.ui.screens.plantContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.remote.responses.GardenRequest
import com.pdm.viridis.data.remote.responses.UserPlantRequest
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.repository.Plant.PlantRepository
import com.pdm.viridis.data.repository.UserPlant.UserPlantRepository
import com.pdm.viridis.ui.screens.gardenContent.GardenContentViewModel
import com.pdm.viridis.ui.screens.gardenCreation.gardenName.GardenNameViewModel
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID

class PlantContentViewModel(
	private val repo: UserPlantRepository,
	private val authRepo: AuthRepository,
	private val gardenRepo : GardenRepository
) : ViewModel() {

	val saving = MutableStateFlow(false)
	val error = MutableStateFlow<String?>(null)

	private val _showSuccessSheet = MutableStateFlow(false)
	val showSuccessSheet: StateFlow<Boolean> = _showSuccessSheet

	private val _showShadeMismatchDialog = MutableStateFlow(false)
	val showShadeMismatchDialog: StateFlow<Boolean> = _showShadeMismatchDialog

	private val _gardenName = MutableStateFlow<String?>(null)
	val gardenName: StateFlow<String?> = _gardenName

	fun resetStates() {
		_showSuccessSheet.value = false
		_showShadeMismatchDialog.value = false
		_gardenName.value = null
		error.value = null
	}

	fun checkShadeAndSave(
		gardenId: String,
		plantId: String,
		plantShadeLevel: String,
		forceSave: Boolean = false
	) {
		viewModelScope.launch {
			saving.value = true
			try {
				val garden = gardenRepo.getGarden(gardenId)
				val gardenShadeLevel = garden.shadeLevel
				_gardenName.value = garden.name

				if (forceSave || gardenShadeLevel.equals(plantShadeLevel, ignoreCase = true)) {
					savePlant(gardenId, plantId)
				} else {
					_showShadeMismatchDialog.value = true
				}
			} catch (e: Exception) {
				error.value = e.message ?: "Error checking shade levels"
			} finally {
				saving.value = false
			}
		}
	}

	fun confirmSaveAnyway(gardenId: String, plantId: String) {
			checkShadeAndSave(gardenId, plantId, "", true)
	}

	fun savePlant(gardenId: String,plantId:String) = viewModelScope.launch {
		saving.value = true
		error.value = null
		try {
			val token = authRepo.token.first() ?: return@launch
			val userId = extractUidFromToken(token) ?: return@launch

			val req = UserPlantRequest(
				id= plantId
			)
			println("Garden Id: $gardenId")
			println("🌿 PLANT ID viewmodel: $plantId")
			repo.addPlant(userId, gardenId, req)
			_showSuccessSheet.value = true
			//saving.value = false
		} catch (e: Exception) {
			//saving.value = false
			error.value = e.message ?: "Failed to save Plant"
		} finally {
		    saving.value = false
		}
	}

	fun dismissShadeMismatchDialog() {
		_showShadeMismatchDialog.value = false
	}

	fun dismissSuccessSheet() {
		_showSuccessSheet.value = false
	}

	companion object {
		fun Factory(): ViewModelProvider.Factory = viewModelFactory {
			initializer {
				val app = (this[APPLICATION_KEY] as ViridisApplication)
				val plantRepository = app.appProvider.provideUserPlantRepository()
				val authRepository = app.appProvider.provideAuthRepository()
				val gardenRepository = app.appProvider.provideGardenRepository()
				PlantContentViewModel( plantRepository, authRepository, gardenRepository)
			}
		}
	}
}
