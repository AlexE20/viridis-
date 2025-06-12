package com.example.viridis.ui.screens.activeNotifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viridis.R
import com.example.viridis.Navigation.SignIn
import com.example.viridis.ui.components.layouts.CustomTopBar
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun NotificationScreen(navController: NavController) {
    CustomTopBar(navController = navController){
        Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(BackgroundColor),
                horizontalAlignment = Alignment.Start
            ){
                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(400.dp),
                    text = "Would you like to get reminders from Viridis?",
                    style = TextStyle(
                        fontFamily = urbanistFont,
                        fontSize = 30.sp,
                        color = Color(0xFF014946)
                    )
                )
            }

        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painterResource(id = R.drawable.notification_image),
            contentDescription = "Notification image",
            modifier = Modifier.size(300.dp)

        )

        Spacer(modifier = Modifier.height(150.dp))

        CustomButton(
            onClick = { navController.navigate(SignIn) },
            text = "Continue"
        )

    }

}
