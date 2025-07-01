package com.pdm.viridis.ui.screens.generalProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.components.layouts.CustomScaffold
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.components.buttons.CustomIconButton
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.ProfileSettings
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.cards.ProfileStatCard
import com.pdm.viridis.ui.theme.Pink40
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pdm.viridis.ui.components.BottomSheets.AlertBottomSheet
import com.pdm.viridis.ui.components.badges.BadgeItem
import com.pdm.viridis.ui.components.badges.BadgeRow
import com.pdm.viridis.ui.screens.addedPlantDetail.AddedPlantDetailViewModel
import com.pdm.viridis.utils.ConnectivityObserver


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
    ) {

    val navigator = LocalNavigator.currentOrThrow
    val user by viewModel.user.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isConnected by viewModel.isConnected.collectAsState()

    LaunchedEffect(Unit) {
        ConnectivityObserver.observe(context).collect { connected ->
            viewModel.setConnectedState(connected)
        }

        viewModel.loadUserStreak()
    }

    CustomScaffold() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            CustomIconButton(
                icon = Icons.Filled.Settings,
                onClick = { navigator.push(ProfileSettings) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                containerColor = SecondaryAccent,
                contentColor = BackgroundColor
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(70.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(SecondaryAccent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "User Icon",
                        tint = BackgroundColor,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Username",
                    fontSize = 20.sp,
                    fontFamily = urbanistFont,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )

                Spacer(modifier = Modifier.height(32.dp))

                //pablo chill esta quemado porque no tengo estos datos de la api es para testear

                ProfileStatCard(
                    icon = Icons.Default.LocalFlorist,
                    text = "Gardens Owned: 1",
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileStatCard(
                    icon = Icons.Filled.LocalFireDepartment,
                    text = "Active Streaks: ${user.user.currentStreak}"
                )

                if (isConnected) {
                    Spacer(modifier = Modifier.height(16.dp))
                    BadgeRow(activeStreaks = user.user.currentStreak)
                }

                Box(modifier = Modifier.fillMaxSize()) {

                    CustomButton(
                        onClick = { /* logout */ },
                        text = "Log Out",
                        buttonColor = Pink40,
                        textColor = BackgroundColor,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp)
                    )
                }
            }
        }
    }
}

