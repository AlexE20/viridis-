package com.example.viridis.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.viridis.ui.screens.meeting.MeetingScreen
import com.example.viridis.ui.screens.notification.NotificationScreen
import com.example.viridis.ui.screens.home.HomeScreen
import com.example.viridis.ui.screens.profile.ProfileScreen
import com.example.viridis.ui.screens.notifications.NotificationsScreen
import com.viridis.ui.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Home



@Serializable
object Profile

@Serializable
object Notifications
@Serializable
object Notification
@Serializable
object Meeting
@Serializable
object LogIn

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Meeting) {
        composable<Home> { HomeScreen(navController) }
        composable<Profile> { ProfileScreen(navController) }
        composable<Notification> { NotificationScreen(navController) }
        composable<Notifications> {NotificationsScreen(navController)}
        composable<LogIn> { LoginScreen() }
        composable<Meeting> { MeetingScreen(navController) }
    }
}