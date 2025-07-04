package com.pdm.viridis.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.repository.Auth.AuthRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> = _signUpSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail.trim()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }


    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onConfirmPasswordChange(newPassword: String){
        _confirmPassword.value = newPassword
    }

    fun register() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            val result = authRepository.register(email.value.trim(), username.value.trim(), password.value.trim())
            _isLoading.value = false
            result.fold(
                onSuccess = {
                    authRepository.saveUsername(username.value.trim())
                    _signUpSuccess.value = true
                },
                onFailure = { e ->
                    _errorMessage.value = e.message ?: "Registration failed"
                }
            )
        }
    }

    fun resetState() {
        _signUpSuccess.value = false
        _errorMessage.value = null
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication


                SignUpViewModel(
                    authRepository = application.appProvider.provideAuthRepository()
                )
            }
        }
    }
}