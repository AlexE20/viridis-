package com.pdm.viridis.data.repository.Reminder




import ReminderResponse
import android.util.Log
import com.pdm.viridis.data.remote.reminders.ReminderService


import retrofit2.HttpException
import java.io.IOException
import com.pdm.viridis.utils.Result
class ReminderRepositoryImpl(
    private val reminderService: ReminderService
) : ReminderRepository {

    override suspend fun getPendingReminders(token: String): Result<List<ReminderResponse>> {
        return try {
            val response = reminderService.getPendingReminders("Bearer $token")
            if (response.isSuccessful) {
                Result.Success(response.body() ?: emptyList())
            } else {

                Result.Error(HttpException(response))
            }
        } catch (e: IOException) {
            Log.e("TAG", "Error message", e)
            Result.Error(e)
        } catch (e: Exception) {
            Log.e("TAG", "Error message", e)
            Result.Error(e)
        }
    }

    override suspend fun markReminderDone(token: String, reminderId: String): Result<Unit> {
        return try {
            val response = reminderService.markReminderDone("Bearer $token", reminderId)
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error(HttpException(response))
            }
        } catch (e: IOException) {
            Result.Error(e)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteReminder(token: String, reminderId: String): Result<Unit> {
        return try {
            val response = reminderService.deleteReminder("Bearer $token", reminderId)
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error(HttpException(response))
            }
        } catch (e: IOException) {
            Result.Error(e)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
