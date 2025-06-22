package com.pdm.viridis.ui.components.layouts

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.R
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.Navigation.ProfileScreen
import com.pdm.viridis.Navigation.SearchPlantScreen.NotificationScreenContent
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainAccent
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent



// ✅ Ahora NavItem usa Screen directamente
data class NavItem(
	val label: String,
	val icon: ImageVector,
	val screen: Screen
)

@Composable
fun CustomBottomBar() {
	val navigator = LocalNavigator.currentOrThrow
	val currentScreen = navigator.lastItem
	
	val navItems = listOf(
		NavItem("Home", Icons.Outlined.Home, HomeScreen),
		NavItem("Profile", Icons.Outlined.Person, ProfileScreen),
		NavItem(
			"Notifications", ImageVector.vectorResource(id = R.drawable.water_drop),
			NotificationScreenContent
		)
	)
	
	NavigationBar(
		modifier = Modifier.height(90.dp),
		containerColor = MainAccent,
	) {
		navItems.forEach { item ->
			NavigationBarItem(
				icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
				selected = currentScreen == item.screen,
				onClick = {
					if (currentScreen != item.screen) {
						navigator.replace(item.screen)
					}
				},
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = MainColor,
					unselectedIconColor = SecondaryAccent,
					selectedTextColor = Color.Black,
					unselectedTextColor = Color.Gray,
					indicatorColor = BackgroundColor,
				)
			)
		}
	}
}
