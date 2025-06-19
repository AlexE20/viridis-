package com.pdm.viridis.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pdm.viridis.R
import com.pdm.viridis.ui.components.cards.ReminderCard
import com.pdm.viridis.ui.components.layouts.CustomScaffold
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun NotificationsScreen(navController: NavHostController) {
    CustomScaffold(navController = navController) {
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

            //girl relax this is for testing purposes only

            Spacer(modifier = Modifier.height(24.dp))

            val checked1 = remember { mutableStateOf(false) }
            val checked2 = remember { mutableStateOf(false) }
            val checked3 = remember { mutableStateOf(false) }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ReminderCard(
                    plantName = "Aloe Vera",
                    location = "Daniel's Studio",
                    dueDate = "8/5/2024",
                    imagePainter = painterResource(R.drawable.login_header_image),
                    isDue = false,
                    isChecked = checked1.value,
                    onCheckedChange = { checked1.value = it }
                )

                ReminderCard(
                    plantName = "Aloe Vera",
                    location = "Daniel's Studio",
                    dueDate = "2/5/2024",
                    imagePainter = painterResource(R.drawable.login_header_image),
                    isDue = true,
                    isChecked = checked2.value,
                    onCheckedChange = { checked2.value = it }
                )

                ReminderCard(
                    plantName = "Aloe Vera",
                    location = "Daniel's Studio",
                    dueDate = "3/5/2024",
                    imagePainter = painterResource(R.drawable.login_header_image),
                    isDue = false,
                    isChecked = checked3.value,
                    onCheckedChange = { checked3.value = it }
                )
            }
        }
    }
}


//@Composable
//fun NotificationsScreen(navController: NavHostController, viewModel: NotificationsViewModel = viewModel()) {
//    val reminders by viewModel.reminders.collectAsState()
//
//    CustomScaffold(navController = navController) {
//        Column(
//            modifier = Modifier
//                .background(BackgroundColor)
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Text("Watering Reminder", fontSize = 22.sp)
//            Spacer(Modifier.height(8.dp))
//            Text(
//                text = "Do not let your lovely plants dehydrate, they deserve to be loved too!",
//                fontSize = 14.sp
//            )
//
//            Spacer(Modifier.height(24.dp))
//
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(reminders) { reminder ->
//                    ReminderCard(
//                        plantName = reminder.plantName,
//                        location = reminder.location,
//                        dueDate = reminder.dueDate,
//                        imagePainter = painterResource(id = R.drawable.login_header_image), // temporal
//                        isDue = reminder.isDue,
//                        isChecked = reminder.isChecked,
//                        onCheckedChange = { viewModel.toggleChecked(reminder.id) }
//                    )
//                }
//            }
//        }
//    }
//}


