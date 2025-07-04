package com.pdm.viridis.ui.screens.profileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.AboutUs
import com.pdm.viridis.Navigation.AppThemeSettings
import com.pdm.viridis.Navigation.NotificationSetting
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.cards.SettingCard
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.theme.*

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingsScreen(
) {

    val navigator = LocalNavigator.currentOrThrow

    CustomTopBar {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Settings",
                fontSize = 30.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(SecondaryAccent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "User icon",
                        tint = BackgroundColor,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

            }

            Spacer(modifier = Modifier.height(24.dp))

            SettingCard(
                title = "General",
                about = "Notifications",
                icon = Icons.Outlined.Notifications
            ) {
                navigator.push(NotificationSetting)
            }

            SettingCard(
                title = "Help",
                about = "About Us",
                icon = Icons.Outlined.Mood
            ) {
                navigator.push(AboutUs)
            }
            SettingCard(
                title = "System Setting",
                about = "App theme",
                icon = Icons.Outlined.WbSunny
            ){
                navigator.push(AppThemeSettings)
            }
        }
    }
}
