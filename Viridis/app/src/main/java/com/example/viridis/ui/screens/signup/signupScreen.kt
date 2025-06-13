package com.example.viridis.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viridis.R
import com.example.viridis.ui.components.buttons.CustomButton
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.viridis.Navigation.Home
import com.example.viridis.Navigation.LogIn
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.textfields.AuthTextField
import com.example.viridis.ui.theme.urbanistFont
import com.example.viridis.ui.components.ImageHeaderScaffold

@ExperimentalMaterial3Api
@Composable
fun signupScreen(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passConfirm by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    val onUserChange = { newValue: String -> user = newValue }
    val onEmailChange = { newValue: String -> email = newValue }
    val onPasswordChange = { newValue: String -> password = newValue }
    val onPassConfirmChange = { newValue: String -> passConfirm = newValue }

    ImageHeaderScaffold(
        navController = navController,
        imageRes = R.drawable.signup_header_image,
        imageHeight = 240.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp)
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Become a master gardener",
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor,
                style = TextStyle(
                    fontFamily = urbanistFont,
                    fontSize = 30.sp,
                    color = Color(0xFF014946)
                )
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Create an account",
                fontSize = 18.sp,
                color = MainColor,
                modifier = Modifier.padding(vertical = 8.dp),
                style = TextStyle(
                    fontFamily = urbanistFont,
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                value = user,
                onValueChange = onUserChange,
                label = "Username",
                leadingIcon = Icons.Filled.AccountCircle
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                leadingIcon = Icons.Filled.Mail
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = "Password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                showPassword = showPassword,
                onTogglePassword = { showPassword = !showPassword }
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = passConfirm,
                onValueChange = onPassConfirmChange,
                label = "Confirm Password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                showPassword = showConfirmPassword,
                onTogglePassword = { showConfirmPassword = !showConfirmPassword }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Sign Up",
                onClick = {navController.navigate(Home)}
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "You already have an account?",
                fontSize = 14.sp,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                text = "Sign In",
                onClick = { navController.navigate(LogIn) }
            )
        }
    }
}


