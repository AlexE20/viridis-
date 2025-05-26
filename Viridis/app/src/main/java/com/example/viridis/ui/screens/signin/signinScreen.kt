package com.example.viridis.ui.screens.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viridis.R
import com.example.viridis.Navigation.LogIn
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.components.buttons.CustomIconTextButton


@Composable
fun signinScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ){
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MainColor
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "One more thing!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Let’s start this journey and get your plants\nhappy!",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        CustomIconTextButton(
            onClick = { navController.navigate(LogIn)},
            text = "Sign in with Email",
            imageVector = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomIconTextButton(
            onClick = { navController.navigate(LogIn)},
            text = "Sign in with Google",
            painter = painterResource(id = R.drawable.signin_google)
        )
    }
}
