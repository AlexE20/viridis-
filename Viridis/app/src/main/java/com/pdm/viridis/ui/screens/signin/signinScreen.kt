package com.pdm.viridis.ui.screens.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.icons.Icons
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.LoginScreen
import com.pdm.viridis.R
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.components.buttons.CustomIconTextButton
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun signinScreen() {
    val navigator = LocalNavigator.currentOrThrow
    
    CustomTopBar(){
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(BackgroundColor),
                horizontalAlignment = Alignment.Start
            ){
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "One more thing!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor,
                    style = TextStyle(
                        fontFamily = urbanistFont,
                        fontSize = 30.sp,
                        color = Color(0xFF014946)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Let’s start this journey and get your plants happy!",
                    fontSize = 16.sp,
                    color = MainColor,
                    style = TextStyle(
                        fontFamily = urbanistFont,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        CustomIconTextButton(
            onClick = { navigator.push(LoginScreen)},
            text = "Sign in with Email",
            imageVector = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomIconTextButton(
            onClick = { navigator.push(LoginScreen)},
            text = "Sign in with Google",
            painter = painterResource(id = R.drawable.signin_google)
        )
    }
}
