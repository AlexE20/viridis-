package com.pdm.viridis.ui.screens.generalProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.User
import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Garden.GardenRepository
import com.pdm.viridis.data.repository.UserInfo.UserInfoRepository
import com.pdm.viridis.ui.screens.gardenContent.GardenContentViewModel
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userInfoRepository: UserInfoRepository,
    private val authRepository: AuthRepository,
    private val gardenRepository: GardenRepository
) : ViewModel() {

    private val _user = MutableStateFlow(User.empty())
    val user: StateFlow<User> = _user

    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val _gardenCount = MutableStateFlow(0)
    val gardenCount : StateFlow<Int> = _gardenCount

    fun setConnectedState(state: Boolean) {
        _isConnected.value = state
    }

    fun loadUserStreak() {
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            val result = userInfoRepository.updateStreak(userId)
            _user.value = result
        }
    }

    fun loadGardenCount(){
        viewModelScope.launch {
            val token = authRepository.token.first() ?: return@launch
            val userId = extractUidFromToken(token) ?: return@launch

            gardenRepository.getLocalGardens(userId).collect { gardens ->
                _gardenCount.value = gardens.size
            }
        }
    }

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            authRepository.clearToken()
            onLoggedOut()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                val userRepo = app.appProvider.provideUserInfoRepository()
                val authRepository = app.appProvider.provideAuthRepository()
                val gardenRepository = app.appProvider.provideGardenRepository()
                ProfileViewModel(userRepo, authRepository, gardenRepository)
            }
        }
    }
}
