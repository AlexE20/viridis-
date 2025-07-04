package com.pdm.viridis.ui.screens.activeNotifications

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.R
import com.pdm.viridis.Navigation.SigninScreen
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun NotificationScreen() {
	val context = LocalContext.current
	val navigator = LocalNavigator.currentOrThrow
	
	val requestPermissionLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.RequestPermission()
	) { isGranted: Boolean ->
		if (isGranted) {
			Toast.makeText(context, "Notifications Activated", Toast.LENGTH_SHORT).show()
		} else {
			Toast.makeText(context, "Notifications Delegated", Toast.LENGTH_SHORT).show()
		}
		navigator.replaceAll(SigninScreen)
	}
	
	CustomTopBar {
		Spacer(modifier = Modifier.height(30.dp))
		
		Column(
			modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(BackgroundColor),
			horizontalAlignment = Alignment.Start
		) {
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
			text = "Continue",
			onClick = {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
					requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
				} else {
					navigator.replaceAll(SigninScreen)
				}
			}
		)
	}
}
