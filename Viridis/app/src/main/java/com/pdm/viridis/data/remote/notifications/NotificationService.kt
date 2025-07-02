package com.pdm.viridis.data.remote.notifications



import com.pdm.viridis.data.remote.responses.FcmTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface NotificationService {
    @POST("api/users/update-token")
    suspend fun updateFcmToken(
        @Body request: FcmTokenRequest
    ): Response<Unit>
}
