package com.pdm.viridis.ui.screens.meeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.pdm.viridis.ViridisApplication
import com.pdm.viridis.data.repository.Auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MeetingViewModel(private val authRepository: AuthRepository) : ViewModel() {
	private val _hasToken = MutableStateFlow<Boolean?>(null)
	val hasToken: StateFlow<Boolean?> = _hasToken
	
	fun checkToken() {
		viewModelScope.launch {
			val user = FirebaseAuth.getInstance().currentUser
			_hasToken.value = user != null
		}
	}
	
	companion object {
		val Factory: ViewModelProvider.Factory = viewModelFactory {
			initializer {
				val app =
					this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
				MeetingViewModel(authRepository = app.appProvider.provideAuthRepository())
			}
		}
	}
}
