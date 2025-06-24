package com.pdm.viridis.ui.screens.meeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.Navigation.ActiveNotificationScreen
import com.pdm.viridis.R
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.theme.baloo2Font

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
            //NEED TO CHANGE THIS AT THE END
            onClick = { navigator.push(ActiveNotificationScreen) },
            text = "Let's go"
        )
    }
}

