package com.example.viridis.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.viridis.HomeScreen
import com.example.viridis.ProfileScreen
import com.example.viridis.R
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainAccent
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.SecondaryAccent
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.viridis.NotificationScreen
import com.example.viridis.ui.screens.notifications.NotificationsScreen

data class NavItem(val label: String, val icon: ImageVector, val screen: Screen)

@Composable
fun CustomBottomBar() {
    val navigator = LocalNavigator.currentOrThrow

    val navItems = listOf(
        NavItem("Home", Icons.Outlined.Home, HomeScreen),
        NavItem("Profile", Icons.Outlined.Person, ProfileScreen),
        NavItem("Notifications", ImageVector.vectorResource(id = R.drawable.water_drop), NotificationScreen)
    )

    NavigationBar(
        modifier = Modifier.height(90.dp),
        containerColor = MainAccent,
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                selected = false, // Voyager no tiene backstack entry observable directo
                onClick = {
                    navigator.push(item.screen)
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
