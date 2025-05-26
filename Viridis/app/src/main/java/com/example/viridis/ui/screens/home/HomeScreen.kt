package com.example.viridis.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.viridis.navigation.Creation
import com.example.viridis.navigation.Home
import com.example.viridis.ui.components.CustomScaffold
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.urbanistFont

@Composable
fun HomeScreen(navController: NavHostController) {
   CustomScaffold(navController = navController) {
      Column(
         modifier = Modifier
            .background(BackgroundColor)
            .padding(horizontal = 5.dp)
            .padding(16.dp),
         verticalArrangement = Arrangement.Top,
         horizontalAlignment = Alignment.Start
      )
      {
         Spacer(modifier = Modifier.height(20.dp))
         Text(
            text = "Hello There!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MainColor,
            lineHeight = 40.sp,
            style = TextStyle(
               fontFamily = urbanistFont,
               fontSize = 30.sp,
               color = Color(0xFF014946)
            )
         )

         Spacer(modifier = Modifier.height(20.dp))

         Text(
            text = "We are glad you are here. Let’s pay your gardens a visit, collect information and watch your plants thrive!",
            fontSize = 15.sp, color = MainColor,
            lineHeight = 20.sp

         )
      }
      Column(
         modifier = Modifier
            .background(BackgroundColor)
            .padding(16.dp).fillMaxSize(),
         verticalArrangement = Arrangement.Top,
         horizontalAlignment = Alignment.CenterHorizontally
      ) {

         CustomButton("Add Garden",
            onClick = {navController.navigate(Creation)},
            modifier = Modifier.width(351.dp)
         )
      }
      LazyColumn {
      //Here will go the gardens

      }
   }
}