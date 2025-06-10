package com.example.viridis.ui.screens.searchPlant

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.viridis.ui.components.CustomTopBar

@ExperimentalMaterial3Api
@Composable
fun searchPlantScreen(navController : NavController) {

    CustomTopBar(navController) {
        
    }
    Text("Search Plant")
}