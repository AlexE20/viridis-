package com.pdm.viridis.ui.screens.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pdm.viridis.ui.components.layouts.CustomScaffold
import com.pdm.viridis.ui.theme.BackgroundColor

@Composable
fun ProfileScreen(navController: NavHostController) {
    CustomScaffold(navController = navController) {
        Column(
            modifier = Modifier.background(BackgroundColor).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Profile Screen", fontSize = 24.sp)
        }
    }
}
