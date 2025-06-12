package com.example.viridis.ui.components.layouts

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.viridis.R
import com.example.viridis.Navigation.Home
import com.example.viridis.Navigation.Notifications
import com.example.viridis.Navigation.Profile
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainAccent
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.SecondaryAccent

data class NavItem(val label: String, val icon: ImageVector, val route: String)

@Composable
fun CustomBottomBar(navController: NavHostController) {
    val navItems = listOf(
        NavItem("Home", Icons.Outlined.Home, Home::class.qualifiedName!!),
        NavItem("Profile", Icons.Outlined.Person, Profile::class.qualifiedName!!),
        NavItem("Notifications", ImageVector.vectorResource(id = R.drawable.water_drop), Notifications::class.qualifiedName!!)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route


    NavigationBar(
        modifier = Modifier.height(90.dp),
        containerColor = MainAccent,
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                selected = currentDestination == item.route,
                onClick = {
                    if (currentDestination != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
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
