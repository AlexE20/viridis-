package com.example.viridis




import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.viridis.data.viewModel.GardenViewModel
import com.example.viridis.ui.screens.gardenContent.GardenContentScreen as GardenContentScreenContent
import com.example.viridis.ui.screens.home.HomeScreen as HomeScreenContent
import com.example.viridis.ui.screens.home.creation.GardenName
import com.example.viridis.ui.screens.home.creation.GardenShade
import com.example.viridis.ui.screens.login.LoginScreen as LoginScreenUI
import com.example.viridis.ui.screens.login.LoginViewModel
import com.example.viridis.ui.screens.meeting.MeetingScreen as MeetingScreenUI
import com.example.viridis.ui.screens.notification.NotificationScreen as NotificationScreenUI
import com.example.viridis.ui.screens.profile.ProfileScreen as ProfileScreenUI
import com.example.viridis.ui.screens.searchPlant.searchPlantScreen
import com.example.viridis.ui.screens.signin.signinScreen
import com.example.viridis.ui.screens.signup.signupScreen
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
