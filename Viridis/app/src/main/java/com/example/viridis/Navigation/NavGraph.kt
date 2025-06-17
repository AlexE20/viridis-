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
import com.example.viridis.ui.screens.addedPlantDetail.addedPlantDetailScreen
import com.example.viridis.ui.screens.gardenCreation.gardenShade.GardenShade
import com.example.viridis.ui.screens.home.HomeScreen
import com.example.viridis.ui.screens.profile.ProfileScreen
import com.example.viridis.ui.screens.notifications.NotificationsScreen
import com.example.viridis.ui.screens.login.LoginScreen
import com.example.viridis.ui.screens.signin.signinScreen
import com.example.viridis.ui.screens.signup.signupScreen
import com.example.viridis.ui.screens.gardenContent.GardenContentScreen
import com.example.viridis.ui.screens.gardenCreation.GardenName
import com.example.viridis.ui.screens.login.LoginViewModel
import com.example.viridis.ui.screens.plantContent.PlantContentScreen
import com.example.viridis.ui.screens.searchPlant.PlantSearchViewModel
import com.example.viridis.ui.screens.searchPlant.SearchPlantScreen
import kotlinx.serialization.Serializable

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Meeting) {

        composable<Meeting> { MeetingScreen(navController) }
        composable<Notification> { NotificationScreen(navController) }

        composable<LogIn> {
            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
            LoginScreen(navController,viewModel=loginViewModel)
        }
        composable<SignUp> {
            //Declaration of the view model
            signupScreen(navController /* THE VIEWMODEL*/)
        }
        composable<SignIn> {
            //Declaration of the view model
            signinScreen(navController /* THE VIEWMODEL*/)
        }
        composable<Home> {
            val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(navController, viewModel = homeViewModel)
        }
        /*
        composable(
            route = "gardenContent/{gardenId}/{gardenName}",
            arguments = listOf(
                navArgument("gardenId") { type = NavType.IntType },
                navArgument("gardenName") { type = NavType.StringType }
            )
        ) {
            GardenContentScreen(navController = navController)
        } */

        composable<Profile> {
            //Declaration of the view model
            ProfileScreen(navController /* THE VIEWMODEL*/)
        }
        composable<Notifications> {
            //Declaration of the view model
            NotificationsScreen(navController /* THE VIEWMODEL*/)
        }
        composable<CreationName> {
            //Declaration of the view model
            GardenName(navController /* THE VIEWMODEL*/)
        }
        composable<CreationShade> {
            //Declaration of the view model
            GardenShade(navController /* THE VIEWMODEL*/)
        }

        composable<SearchPlant> {
            val searchViewModel : PlantSearchViewModel = viewModel(factory = PlantSearchViewModel.Factory)
            SearchPlantScreen(navController, searchViewModel)
        }
        composable<PlantContentNavigation> {
            PlantContentScreen(navController)
        }
        composable<PlantDetailNavigation> {
            addedPlantDetailScreen(navController)
        }
        composable<GardenContentNavigation> {
            GardenContentScreen(navController)
        }
        /*
        composable("plant_detail/{plantId}") { backStackEntry ->
            val plantId = backStackEntry.arguments?.getString("plantId")?: ""
            PlantContentScreen(navController = navController, plantId = plantId)
        }

        composable(
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
        } */
    }
}
