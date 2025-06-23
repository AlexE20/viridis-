package com.pdm.viridis.Navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pdm.viridis.ui.screens.home.HomeViewModel
import com.pdm.viridis.ui.screens.meeting.MeetingScreen
import com.pdm.viridis.ui.screens.activeNotifications.NotificationScreen
//import com.example.viridis.ui.screens.addedPlantDetail.addedPlantDetailScreen
import com.pdm.viridis.ui.screens.home.HomeScreen
import com.pdm.viridis.ui.screens.profile.ProfileScreen
import com.pdm.viridis.ui.screens.notifications.NotificationsScreen
import com.pdm.viridis.ui.screens.login.LoginScreen
import com.pdm.viridis.ui.screens.signin.signinScreen
import com.pdm.viridis.ui.screens.signup.signupScreen
import com.pdm.viridis.ui.screens.gardenContent.GardenContentScreen
//import com.example.viridis.ui.screens.gardenCreation.
import com.pdm.viridis.ui.screens.gardenCreation.gardenName.GardenNameScreen
import com.pdm.viridis.ui.screens.gardenCreation.gardenName.GardenNameViewModel
import com.pdm.viridis.ui.screens.gardenCreation.gardenShade.GardenShadeScreen
import com.pdm.viridis.ui.screens.gardenCreation.gardenShade.GardenShadeViewModel
import com.pdm.viridis.ui.screens.login.LoginViewModel
import com.pdm.viridis.ui.screens.plantContent.PlantContentScreen
import com.pdm.viridis.ui.screens.plantContent.PlantContentViewModel
import com.pdm.viridis.ui.screens.searchPlant.PlantSearchViewModel
import com.pdm.viridis.ui.screens.searchPlant.SearchPlantScreen

@SuppressLint("WrongNavigateRouteType")
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
            signupScreen(navController)
        }
        composable<SignIn> {
            signinScreen(navController)
        }
        composable<Home> {
            val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(navController, viewModel = homeViewModel)
        }

        composable<Profile> {
            ProfileScreen(navController)
        }
        composable<Notifications> {
            NotificationsScreen(navController)
        }
        composable<CreationName> {
            val viewModel : GardenNameViewModel = viewModel(factory = GardenNameViewModel.Factory)
            GardenNameScreen(navController, viewModel, {
                val name= viewModel.getGardenName()
                navController.navigate(CreationShade(name))
            } )
        }
        composable<CreationShade> {
            val searchViewModel : GardenShadeViewModel = viewModel(factory = GardenShadeViewModel.Factory)
            GardenShadeScreen(navController, searchViewModel)
        }

        composable<SearchPlant> {
            val searchViewModel: PlantSearchViewModel = viewModel(factory = PlantSearchViewModel.Factory)
            SearchPlantScreen(
                navController = navController,
                viewModel = searchViewModel,
                onPlantClick = { plant ->
                    val plantEntry = navController.getBackStackEntry(PlantContent)
                    plantEntry.savedStateHandle["plant"] = plant
                    navController.navigate(PlantContent)
                }

            )
        }

        composable<PlantContent> {
            val viewModel: PlantContentViewModel = viewModel(factory = PlantContentViewModel.Factory)
            PlantContentScreen(navController = navController, viewModel = viewModel)
        }


        //NO FUNCIONA, DESCOMENTAR CUANDO SE TENGA PlantRepository
        composable<PlantDetailNavigation> {
            //addedPlantDetailScreen(navController)
        }
        composable(
            route = "gardenContent/{gardenId}/{gardenName}",
            arguments = listOf(navArgument("gardenId") { type = NavType.StringType },
                navArgument("gardenName") { type= NavType.StringType })
        ) { backStackEntry ->
            val gardenId = backStackEntry.arguments?.getString("gardenId") ?: ""
            val gardenName=backStackEntry.arguments?.getString("gardenName")?:""
            GardenContentScreen(navController, gardenId,gardenName)
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