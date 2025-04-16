package com.example.viridis.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viridis.R
import com.example.viridis.ui.theme.urbanistFont
import java.nio.file.WatchEvent




@Composable
fun Notification(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FBE7)),
        Alignment.TopCenter

    ) {

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start

        ) {
            Spacer(modifier = Modifier.height(20.dp))
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF014946),
                    modifier = Modifier.size(100.dp)

                )

            }

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(400.dp),
                    text = "Would you like to get reminders from Viridis?",
                    style = TextStyle(
                        fontFamily = urbanistFont,
                        fontSize = 30.sp,
                        color = Color(0xFF014946)
                    )
                )
                Spacer(modifier = Modifier.height(100.dp))
                Image(
                    painterResource(id = R.drawable.notification_image),
                    contentDescription = "Notification image",
                    modifier = Modifier.size(300.dp)

                )
                Spacer(modifier = Modifier.height(150.dp))

                Button(
                    onClick = { println("Botton pressed") },
                    modifier = Modifier.width(370.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF014946),
                        contentColor = Color.White,
                        disabledContentColor = Color.Blue
                    )
                ) {
                    Text(text = "Continue")
                }
            }

        }
    }
}
