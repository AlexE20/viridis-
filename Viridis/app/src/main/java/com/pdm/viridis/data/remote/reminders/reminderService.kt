package com.pdm.viridis.data.remote.reminders


import ReminderResponse
import retrofit2.http.*
import retrofit2.Response

interface ReminderService {

    @GET("api/reminders/")
    suspend fun getPendingReminders(
        @Header("Authorization") token: String
    ): Response<List<ReminderResponse>>


    @PATCH("api/reminders/{reminderId}/done")
    suspend fun markReminderDone(
        @Header("Authorization") token: String,
        @Path("reminderId") reminderId: String
    ): Response<Unit>


    @DELETE("api/reminders/{reminderId}")
    suspend fun deleteReminder(
        @Header("Authorization") token: String,
        @Path("reminderId") reminderId: String
    ): Response<Unit>
}
