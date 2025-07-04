package com.pdm.viridis.ui.screens.login

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.components.textfields.AuthTextField
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.Navigation.SignupScreen
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun LoginScreen( viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)) {
    
    val navigator = LocalNavigator.currentOrThrow
    
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val loginSuccess by viewModel.loginSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navigator.replaceAll(HomeScreen)
            viewModel.resetLoginState()
        }
    }

    errorMessage?.let { error ->
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
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
                color = MainColor,
                style = TextStyle(
                    fontFamily = urbanistFont,
                    fontSize = 30.sp,
                    color = MainColor
                )
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
                onClick = { navigator.push(SignupScreen) },
            )
        }
    }
}
