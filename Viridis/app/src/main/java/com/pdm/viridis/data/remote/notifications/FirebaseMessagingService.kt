package com.pdm.viridis.data.remote.notifications

import com.pdm.viridis.MainActivity
import com.pdm.viridis.R



import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pdm.viridis.data.remote.RetrofitInstance
import com.pdm.viridis.data.remote.responses.FcmTokenRequest
import com.pdm.viridis.data.repository.Auth.AuthRepositoryImpl
import com.pdm.viridis.utils.dataStore
import com.pdm.viridis.utils.extractUidFromToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MyFirebaseMessagingService() : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "✅ New token: $token")

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val dataStore = applicationContext.dataStore


                val authRepo = AuthRepositoryImpl(dataStore, RetrofitInstance.authService)


                val jwt = authRepo.token.firstOrNull() ?: return@launch

                // Extract UID from JWT token (implement this function as needed)
                val userId = extractUidFromToken(jwt) ?: return@launch

                // Send token to backend
                val response = RetrofitInstance.notificationService.updateFcmToken(
                    FcmTokenRequest(userId, token)
                )

                if (response.isSuccessful) {
                    Log.d("FCM", "✅ Token sent to server")
                } else {
                    Log.e("FCM", "❌ Server error: ${response.code()} - ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("FCM", "❌ Error sending FCM token: ${e.localizedMessage}")
            }
        }
    }



    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM", "📩 Message from: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            val title = it.title ?: "New Notification"
            val body = it.body ?: ""
            Log.d("FCM", "📝 Notification - Title: $title, Body: $body")
            showNotification(title, body)
        }

        // Check if message contains data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCM", "📦 Data payload: ${remoteMessage.data}")
            // Handle data if needed
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun showNotification(title: String, messageBody: String) {
        val channelId = "default_channel"
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.water_drop)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Android 8+ requires a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
}
