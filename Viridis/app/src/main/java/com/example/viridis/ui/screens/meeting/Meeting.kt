package com.example.viridis.ui.screens.meeting

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.viridis.R
import com.example.viridis.NotificationScreen
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.theme.baloo2Font

@Composable
fun MeetingScreen() {
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FBE7)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Viridis",
            style = TextStyle(
                fontFamily = baloo2Font,
                fontSize = 90.sp,
                color = Color(0xFF014946)
            )
        );
        Text(
            text = "plant & garden care",
            style = TextStyle(
                fontFamily = baloo2Font,
                fontSize = 20.sp,
                color = Color(0xFF014946)
            )
        )
        Image(
            painter = painterResource(id = R.drawable.meeting_image),
            contentDescription = "Meeting logo"
        )
        Spacer(modifier = Modifier.height(70.dp))
        CustomButton(
            onClick = { navigator.push(NotificationScreen) },
            text = "Let's go"
        )
    }
}

