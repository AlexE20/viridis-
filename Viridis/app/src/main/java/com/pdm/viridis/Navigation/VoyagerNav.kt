package com.pdm.viridis.Navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.data.model.Plant
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.ui.screens.aboutUs.AboutUsScreen
import com.pdm.viridis.ui.screens.addedPlantDetail.AddedPlantDetailViewModel
import com.pdm.viridis.ui.screens.addedPlantDetail.addedPlantDetailScreen
import com.pdm.viridis.ui.screens.appThemeSetting.AppThemeSettingScreen
import com.pdm.viridis.ui.screens.gardenCreation.gardenName.GardenNameViewModel
import com.pdm.viridis.ui.screens.login.LoginViewModel
import com.pdm.viridis.ui.screens.signin.signinScreen
import com.pdm.viridis.ui.screens.signup.signupScreen
import kotlinx.serialization.Serializable
import com.pdm.viridis.ui.screens.login.LoginScreen as LoginScreenUI
import com.pdm.viridis.ui.screens.home.HomeScreen as HomeScreenContent
import com.pdm.viridis.ui.screens.gardenContent.GardenContentScreen as GardenContentScreenContent
import com.pdm.viridis.ui.screens.profile.ProfileScreen as ProfileScreenUI
import com.pdm.viridis.ui.screens.activeNotifications.NotificationScreen as ActiveNotificationScreenUI
import com.pdm.viridis.ui.screens.meeting.MeetingScreen as MeetingScreenUI
import com.pdm.viridis.ui.screens.gardenCreation.gardenName.GardenNameScreen as GardenName
import com.pdm.viridis.ui.screens.gardenCreation.gardenShade.GardenShadeScreen as GardenShade
import com.pdm.viridis.ui.screens.searchPlant.SearchPlantScreen as searchPlantScreen
import com.pdm.viridis.ui.screens.profileSettings.ProfileSettingsScreen as ProfileSettingsUI
import com.pdm.viridis.ui.screens.home.HomeViewModel
import com.pdm.viridis.ui.screens.gardenCreation.gardenShade.GardenShadeViewModel
import com.pdm.viridis.ui.screens.notificationSettings.NotificationSettingScreen
import com.pdm.viridis.ui.screens.searchPlant.PlantSearchViewModel
import com.pdm.viridis.ui.screens.notifications.NotificationsScreen
import com.pdm.viridis.ui.screens.plantContent.PlantContentScreenUI
import com.pdm.viridis.ui.screens.plantContent.PlantContentViewModel


@ExperimentalMaterial3Api
@Composable
fun AppNavigation() {
	Navigator(MeetingScreen)
}

@Serializable
object LoginScreen : Screen {
	@Composable
	override fun Content() {
		val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
		LoginScreenUI(viewModel = viewModel)
	}
}

@Serializable
object SignupScreen : Screen {
	@Composable
	override fun Content() {
		signupScreen()
	}
}

@Serializable
object SigninScreen : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		signinScreen()
	}
}

@Serializable
object HomeScreen : Screen {
	@Composable
	override fun Content() {
		val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
		HomeScreenContent(viewModel = viewModel)
	}
}

@Serializable
data class GardenContentScreen(val gardenId: String, val gardenName: String) : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		GardenContentScreenContent(gardenId = gardenId, gardenName = gardenName)
	}
}

@Serializable
object ProfileScreen : Screen {
	@Composable
	override fun Content() {
		ProfileScreenUI()
	}
}

@Serializable
object ActiveNotificationScreen : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		ActiveNotificationScreenUI()
	}
}


@Serializable
object MeetingScreen : Screen {
	@Composable
	override fun Content() {
		MeetingScreenUI()
	}
}

@Serializable
object GardenNameScreen : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		val navigator = LocalNavigator.currentOrThrow
		val viewModel: GardenNameViewModel = viewModel(factory = GardenNameViewModel.Factory)
		GardenName(viewModel = viewModel, onNext = { gardenName->navigator.push(GardenShadeScreen(gardenName)) })
	}
}

@Serializable
data class GardenShadeScreen(val gardenName: String) : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		val viewModel: GardenShadeViewModel = viewModel(factory = GardenShadeViewModel.Factory)
		GardenShade(viewModel = viewModel, gardenName = gardenName)
	}
}

@Serializable
data class SearchPlantScreen(val gardenId: String) : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		val viewModel: PlantSearchViewModel = viewModel(factory = PlantSearchViewModel.Factory)
		searchPlantScreen(viewModel=viewModel,gardenId = gardenId)
	}
}
	
	@Serializable
	object NotificationScreenContent : Screen {
		@OptIn(ExperimentalMaterial3Api::class)
		@Composable
		override fun Content() {
			NotificationsScreen()
		}
	}

	@Serializable
	data class PlantContentScreen(
		val plantId: String,
		val gardenId: String,
		val commonName: String = "",
		val scientificName: String = "",
		val careLevel: String = "",
		val shadeLevel: String = "",
		val watering: String = "",
		val recommendations: List<Recommendation> = emptyList(),
		val imageUrl: String = ""
	) : Screen {
		@OptIn(ExperimentalMaterial3Api::class)
		@Composable
		override fun Content() {
			val viewModel: PlantContentViewModel = viewModel(factory = PlantContentViewModel.Factory())
			PlantContentScreenUI(
				viewModel=viewModel,
				gardenId,
				plantId,
				commonName,
				scientificName,
				careLevel,
				shadeLevel,
				watering,
				recommendations,
				imageUrl
			)
		}
	}

	@Serializable
	data class AddedPlantDetailScreen(
		val id: String,
		val gardenId: String,
		val commonName: String = "",
		val scientificName: String = "",
		val careLevel: String = "",
		val shadeLevel: String = "",
		val watering: String = "",
		val defaultImage: String,
		val recommendations: List<Recommendation> = emptyList(),
		val wateredStreak: Int = 0,
		val lastWateredAt: String?= null
	) : Screen {
		@OptIn(ExperimentalMaterial3Api::class)
		@Composable
		override fun Content() {
			val viewModel: AddedPlantDetailViewModel = viewModel(factory = AddedPlantDetailViewModel.Factory())
			addedPlantDetailScreen(
				viewModel=viewModel,
				id,
				gardenId,
				commonName,
				scientificName,
				careLevel,
				shadeLevel,
				watering,
				defaultImage,
				recommendations,
				wateredStreak,
				lastWateredAt?:""
			)
		}
	}

@Serializable
object  ProfileSettings: Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		ProfileSettingsUI()
	}
}

@Serializable
object  AboutUs: Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		AboutUsScreen()
	}
}

@Serializable
object  NotificationSetting: Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		NotificationSettingScreen()
	}
}

@Serializable
object  AppThemeSettings: Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		AppThemeSettingScreen()
	}
}

