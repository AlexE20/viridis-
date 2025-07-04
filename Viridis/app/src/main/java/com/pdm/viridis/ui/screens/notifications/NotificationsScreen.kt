package com.pdm.viridis.ui.screens.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.pdm.viridis.R
import com.pdm.viridis.ui.components.cards.ReminderCard
import com.pdm.viridis.ui.components.layouts.CustomScaffold
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont
import com.pdm.viridis.utils.toFormattedDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationsScreen(viewModel: NotificationsViewModel = viewModel(factory = NotificationsViewModel.Factory)) {


    val reminders by viewModel.reminders.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Trigger loading once
    LaunchedEffect(Unit) {
        viewModel.loadReminders()
    }

    CustomScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Watering Reminder",
                fontSize = 32.sp,
                color = MainColor,
                lineHeight = 40.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Do not let your lovely plants dehydrate, they deserve to be loved too!",
                fontSize = 15.sp,
                color = MainColor,
                lineHeight = 20.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    color = MainColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else if (error != null) {
                Text("Error: $error", color = MainColor)
            } else if (reminders.isEmpty()) {
                Text("No reminders 🎉", color = MainColor)
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    reminders.forEach { reminder ->
                        val isChecked = remember { mutableStateOf(false) }

                        ReminderCard(
                            plantName = reminder.common_name,
                            location = reminder.garden_name ?: "Unknown location",
                            dueDate = reminder.dueAt.toFormattedDate(),
                            imagePainter = painterResource(R.drawable.login_header_image), // Replace with actual image loading
                            isDue = true, // You may want to compute this based on `dueAt`
                            isChecked = isChecked.value,
                            onCheckedChange = { checked ->
                                isChecked.value = checked
                                if (checked) {
                                    viewModel.markReminderDone(reminder.id)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
