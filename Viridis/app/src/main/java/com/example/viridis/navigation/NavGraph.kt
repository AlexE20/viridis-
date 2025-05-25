package com.example.viridis.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.viridis.ui.screens.meeting.MeetingScreen
import com.example.viridis.ui.screens.notification.NotificationScreen
import com.example.viridis.ui.screens.home.creation.GardenShade
import com.example.viridis.ui.screens.home.HomeScreen
import com.example.viridis.ui.screens.home.creation.GardenName
import com.example.viridis.ui.screens.profile.ProfileScreen
import com.example.viridis.ui.screens.notifications.NotificationsScreen
import com.example.viridis.ui.screens.login.LoginScreen
import com.example.viridis.ui.screens.signin.signinScreen
import com.example.viridis.ui.screens.signup.signupScreen

import kotlinx.serialization.Serializable

@Serializable
object Home
@Serializable
object Creation
@Serializable
object Creation2
@Serializable
object LogIn
@Serializable
object SignUp
@Serializable
object Profile
@Serializable
object SignIn


@Serializable
object Notifications
@Serializable
object Notification
@Serializable
object Meeting

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Meeting) {
        composable<LogIn> { LoginScreen(navController) }
        composable<SignUp> { signupScreen(navController) }
        composable<SignIn> { signinScreen(navController) }
        composable<Home> { HomeScreen(navController) }
        composable<Profile> { ProfileScreen(navController) }
        composable<Notification> { NotificationScreen(navController) }
        composable<Notifications> { NotificationsScreen(navController) }
        composable<Meeting> { MeetingScreen(navController) }
        composable<Creation> { GardenName(navController)  }
        composable<Creation2> { GardenShade(navController) }
    }
}
