package com.viridis.ui.login

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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.viridis.navigation.Home
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.textfields.AuthTextField
import com.example.viridis.ui.screens.logIn.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_header_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 64.dp
                        )
                    )
            )

            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .background(Color(0xFFEAF1D8), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MainColor
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

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
                color = MainColor
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Sign In to your account",
                fontSize = 18.sp,
                color = MainColor,
                modifier = Modifier.padding(vertical = 8.dp)
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
                onValueChange = {viewModel.onEmailChange(email)},
                label = "Username",
                leadingIcon = Icons.Filled.AccountCircle
            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthTextField(
                value = password,
                onValueChange = {viewModel.onPasswordChange(password)},
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
                    viewModel.login(
                        context = context,
                        onSuccess = {

                            navController.navigate(Home)
                        },
                        onError = { errorMsg ->
                            // Show error
                            println(errorMsg) // Or show Snackbar/Toast
                        }
                    )
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
                onClick = { NavController}
            )
        }
    }
}

