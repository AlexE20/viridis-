package com.pdm.viridis

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable


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
	@Composable
	override fun Content() {
		signinScreen()
	}
}

@Serializable
object HomeScreen : Screen {
	@Composable
	override fun Content() {
		val viewModel: GardenViewModel = viewModel(factory = GardenViewModel.Factory)
		HomeScreenContent(viewModel = viewModel)
	}
}

@Serializable
data class GardenContentScreen(val gardenId: String, val gardenName: String) : Screen {
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
object NotificationScreen : Screen {
	@Composable
	override fun Content() {
		NotificationScreenUI()
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
		GardenName()
	}
}

@Serializable
object GardenShadeScreen : Screen {
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		GardenShade()
	}
}

@Serializable
object SearchPlantScreen : Screen {
	@Composable
	override fun Content() {
		searchPlantScreen()
	}
}