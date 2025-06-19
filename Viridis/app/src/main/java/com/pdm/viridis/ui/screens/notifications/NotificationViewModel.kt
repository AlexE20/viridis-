package com.pdm.viridis.ui.screens.notifications

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/*class NotificationsViewModel : ViewModel() {
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders

    init {
        // esto es una simulacion temporal con datos dummy pablo no te maltripees solo es para testear
        _reminders.value = listOf(
            Reminder(1, "Aloe Vera", "Daniel's Studio", "8/5/2024", "", false, false),
            Reminder(2, "Aloe Vera", "Daniel's Studio", "2/5/2024", "", true, false),
        )
    }

    fun toggleChecked(id: Int) {
        _reminders.update { list ->
            list.map {
                if (it.id == id) it.copy(isChecked = !it.isChecked) else it
            }
        }
    }
}*/
