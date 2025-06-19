package com.example.viridis.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viridis.R
import com.example.viridis.ui.components.buttons.CustomButton
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.viridis.Navigation.Home
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.textfields.AuthTextField
import com.example.viridis.Navigation.SignUp
import com.example.viridis.ui.components.layouts.ImageHeaderScaffold
import com.example.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavController,viewModel: LoginViewModel) {

fun LoginScreen(navController: NavController,  viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var showPassword by remember { mutableStateOf(false) }


    ImageHeaderScaffold(
        navController = navController,
        imageRes = R.drawable.login_header_image,
        imageHeight = 320.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp)
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Welcome to Viridis",
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor,
                style = TextStyle(
                    fontFamily = urbanistFont,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sign In to your account",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MainColor,
                style = TextStyle(
                    fontFamily = urbanistFont,
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = "Email",
                leadingIcon = Icons.Filled.AccountCircle
            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = "Password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                showPassword = showPassword,
                onTogglePassword = { showPassword = !showPassword }
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                text = "Sign in",
                onClick = {
                    viewModel.login()

                    navController.navigate(Home)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "You don’t have an account?",
                fontSize = 14.sp,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                text = "Sign up",
                onClick = { navController.navigate(SignUp) }
            )
        }
    }
}

