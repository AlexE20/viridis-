package com.example.viridis.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.viridis.ui.layout.CustomScaffold
import com.example.viridis.ui.theme.BackgroundColor

@Composable
fun HomeScreen(navController: NavHostController) {
   CustomScaffold(navController = navController) {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Text(text = "Welcome to Home!", fontSize = 24.sp, color = Color.Black)

      }
   }
}