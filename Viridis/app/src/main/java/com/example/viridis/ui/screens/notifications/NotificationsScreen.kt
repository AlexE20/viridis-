package com.example.viridis.ui.screens.notifications



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.viridis.ui.components.CustomScaffold
import com.example.viridis.ui.theme.BackgroundColor

@Composable
fun NotificationsScreen(navController: NavHostController) {
    CustomScaffold(navController = navController) {
        Column(
            modifier = Modifier.background(BackgroundColor).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("Notifications Screen", fontSize = 24.sp)
        }
    }
}
