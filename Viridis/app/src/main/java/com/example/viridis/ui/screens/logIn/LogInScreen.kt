package com.example.viridis.ui.screens.logIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.viridis.ui.components.CustomScaffold
import com.example.viridis.ui.components.buttoms.CustomButton
import com.example.viridis.ui.components.textfields.CustomTextfield
import com.example.viridis.ui.theme.BackgroundColor


@ExperimentalMaterial3Api
@Composable
fun LogInScreen(navController: NavHostController){
    CustomScaffold(navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Image()
            Text("Welcome to Viridis")
            Text("Sign In to account")
            //CustomTextfield()
            //CustomTextfield()
            CustomButton("Sign In", onClick = {})
            Text("You don't have an account?")
            CustomButton("Sign Up", onClick = {})
        }
    }
}

