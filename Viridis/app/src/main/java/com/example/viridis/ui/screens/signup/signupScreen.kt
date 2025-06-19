package com.example.viridis.ui.screens.signup

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
import androidx.compose.material.icons.filled.Mail
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viridis.Navigation.Home
import com.example.viridis.Navigation.LogIn
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.textfields.AuthTextField
import com.example.viridis.ui.screens.login.LoginViewModel
import com.example.viridis.ui.theme.urbanistFont
import com.example.viridis.ui.components.layouts.ImageHeaderScaffold

@ExperimentalMaterial3Api
@Composable
fun signupScreen(navController: NavController, viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel .Factory)) {
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                value = username,
                onValueChange = viewModel::onUsernameChange,
                label = "Username",
                leadingIcon = Icons.Filled.AccountCircle
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = email,
                onValueChange = viewModel::onEmailChange,
                label = "Email",
                leadingIcon = Icons.Filled.Mail
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = password,
                onValueChange = viewModel::onPasswordChange,
                label = "Password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                showPassword = showPassword,
                onTogglePassword = { showPassword = !showPassword }
            )

            Spacer(modifier = Modifier.height(15.dp))

            AuthTextField(
                value = confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = "Confirm Password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                showPassword = showConfirmPassword,
                onTogglePassword = { showConfirmPassword = !showConfirmPassword }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Sign Up",
                onClick = { viewModel.register() }
            )
        }
    }
}


