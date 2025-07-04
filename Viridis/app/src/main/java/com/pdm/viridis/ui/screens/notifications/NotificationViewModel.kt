package com.pdm.viridis.ui.screens.notifications

import ReminderResponse
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.model.Reminder

import com.pdm.viridis.data.repository.Auth.AuthRepository
import com.pdm.viridis.data.repository.Auth.AuthRepositoryImpl
import com.pdm.viridis.data.repository.Reminder.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.pdm.viridis.utils.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.filterNot
import kotlin.fold

class NotificationsViewModel(
    private val reminderRepository: ReminderRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _reminders = MutableStateFlow<List< ReminderResponse>>(emptyList())
    val reminders: StateFlow<List<ReminderResponse>> = _reminders

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadReminders() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val token = authRepository.token.first() ?: return@launch
                when (val result = reminderRepository.getPendingReminders(token)) {
                    is Result.Success -> {
                        _reminders.value = result.data
                    }
                    is Result.Error -> {
                        _error.value = result.exception.message
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error message", e)
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun markReminderDone(reminderId: String) {
        viewModelScope.launch {
            try {
                val token = authRepository.token.first() ?: return@launch
                when (val result = reminderRepository.markReminderDone(token, reminderId)) {
                    is Result.Success -> {
                        _reminders.update { list -> list.filterNot { it.id == reminderId } }
                    }
                    is Result.Error -> {

                        _error.value = result.exception.message
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error message", e)
                _error.value = e.message
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as ViridisApplication)
                NotificationsViewModel(
                    app.appProvider.provideReminderRepository(),
                    app.appProvider.provideAuthRepository()
                )
            }
        }
    }
}
