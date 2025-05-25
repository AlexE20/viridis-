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
import androidx.compose.ui.tooling.preview.Preview
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.textfields.AuthTextField



@Composable
fun signupScreen(
    onBack: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    user: String,
    email: String,
    password: String,
    passConfirm: String,
    onUserChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPassConfirmChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

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
                .height(240.dp)
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
                onClick = onBack,
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

        Spacer(modifier = Modifier.height(20.dp))

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
                color = MainColor
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Create an account",
                fontSize = 18.sp,
                color = MainColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                text = "Sign Up",
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "You already have an account?",
                fontSize = 14.sp,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Sign In",
                onClick = onSignUpClick
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun signupScreenPreview() {
    var user by remember { mutableStateOf("Pachamama") }
    var email by remember { mutableStateOf("Pachi66@uca.edu.sv") }
    var password by remember { mutableStateOf("quetzalcoatl123") }
    var passConfirm by remember { mutableStateOf("quetzalcoatl123") }


    signupScreen(
        onBack = {},
        onLoginClick = {},
        onSignUpClick = {},
        user = user,
        email = email,
        password = password,
        passConfirm = passConfirm,
        onUserChange = { user = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onPassConfirmChange = { passConfirm = it }
    )
}

