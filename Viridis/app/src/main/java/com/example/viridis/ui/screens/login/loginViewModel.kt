package com.example.viridis.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.viridis.data.AppProvider

class LoginViewModel : ViewModel() {

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
        context: Context,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank()) {
                onError("Email or password can't be empty")
                return@launch
            }

            // Simulate login success TODO(replace with real auth logic)
            val fakeToken = "fake_token_12345"

            try {
                AppProvider.getInstance(context).getToken();
                onSuccess()
            } catch (e: Exception) {
                onError("Failed to save token: ${e.message}")
            }
        }
    }

    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }
}
