package com.pdm.viridis.data.repository.Reminder

import ReminderResponse
import com.pdm.viridis.utils.Result



interface ReminderRepository {
    suspend fun getPendingReminders(token: String): Result<List<ReminderResponse>>
    suspend fun markReminderDone(token: String, reminderId: String): Result<Unit>
    suspend fun deleteReminder(token: String, reminderId: String): Result<Unit>
}
