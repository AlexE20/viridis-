package com.example.viridis.Navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.viridis.ui.screens.home.HomeViewModel
import com.example.viridis.ui.screens.meeting.MeetingScreen
import com.example.viridis.ui.screens.activeNotifications.NotificationScreen
import com.example.viridis.ui.screens.gardenCreation.gardenShade.GardenShade
import com.example.viridis.ui.screens.home.HomeScreen
import com.example.viridis.ui.screens.gardenCreation.gardenName.GardenName
import com.example.viridis.ui.screens.profile.ProfileScreen
import com.example.viridis.ui.screens.notifications.NotificationsScreen
import com.example.viridis.ui.screens.login.LoginScreen
import com.example.viridis.ui.screens.signin.signinScreen
import com.example.viridis.ui.screens.signup.signupScreen
import com.example.viridis.ui.screens.gardenContent.GardenContentScreen
import com.example.viridis.ui.screens.login.LoginViewModel
import com.example.viridis.ui.screens.searchPlant.searchPlantScreen
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
object SearchPlant

@Serializable
object Notifications

@Serializable
object Notification

@Serializable
object Meeting

@Serializable
object addedPlantDetail

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val loginViewModel: LoginViewModel=viewModel(factory = LoginViewModel.Factory)
    NavHost(navController = navController, startDestination = Meeting) {
        composable<LogIn> { LoginScreen(navController,viewModel=loginViewModel) }
        composable<SignUp> { signupScreen(navController) }
        composable<SignIn> { signinScreen(navController) }
        composable<Home> { HomeScreen(navController, viewModel = viewModel) }
        composable(
            route = "gardenContent/{gardenId}/{gardenName}",
            arguments = listOf(
                navArgument("gardenId") { type = NavType.IntType },
                navArgument("gardenName") { type = NavType.StringType }
            )
        ) {
            GardenContentScreen(navController = navController)
        }

        composable<Profile> { ProfileScreen(navController) }
        composable<Notification> { NotificationScreen(navController) }
        composable<Notifications> { NotificationsScreen(navController) }
        composable<Meeting> { MeetingScreen(navController) }
        composable<Creation> { GardenName(navController) }
        composable<Creation2> { GardenShade(navController) }
        composable<SearchPlant> { searchPlantScreen(navController) }
        /*composable(
            route = "addedPlantDetail/{plantId}",
            arguments = listOf(navArgument("plantId") { type = NavType.StringType })
        ) {
            val viewModel: AddedPlantDetailViewModel = viewModel(
                factory = AddedPlantDetailViewModel.provideFactory(appProvider)
            )

            addedPlantDetailScreen(
                navController = navController,
                viewModel = viewModel,
                appProvider = appProvider
            )
        }*/
    }
}
