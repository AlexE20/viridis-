package com.example.viridis.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.viridis.ViridisApplication
import com.example.viridis.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank()) {
                onError("Email or password can't be empty")
                return@launch
            }

            val fakeToken = "fake_token_12345"

            try {
                userPreferencesRepository.saveToken(fakeToken)
                onSuccess()
            } catch (e: Exception) {
                onError("Failed to save token: ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                val userPrefs = app.appProvider.provideUserPreferencesRepository()
                LoginViewModel(userPrefs)
            }
        }
    }
}
