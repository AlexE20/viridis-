package com.pdm.viridis.ui.screens.signup

import android.widget.Toast
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
import com.pdm.viridis.R
import com.pdm.viridis.ui.components.buttons.CustomButton
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.components.textfields.AuthTextField
import com.pdm.viridis.ui.theme.urbanistFont


@Composable
fun signupScreen(viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel .Factory)) {
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    
    
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current
    
    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            navigator.replace(HomeScreen)
            viewModel.resetState()
        }
    }

    errorMessage?.let {
        LaunchedEffect(it) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
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
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.signup_header_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 64.dp
                        )
                    )
            )

            IconButton(
                onClick = { navigator.pop() },
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

        Spacer(modifier = Modifier.height(15.dp))

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
                modifier = Modifier.padding(vertical = 8.dp)
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