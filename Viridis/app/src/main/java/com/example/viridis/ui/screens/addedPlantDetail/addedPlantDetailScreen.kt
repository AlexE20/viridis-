package com.example.viridis.ui.screens.addedPlantDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.viridis.ui.components.ImageHeaderScaffold
import com.example.viridis.ui.components.buttons.CustomIconButton
import com.example.viridis.ui.components.cards.InfoCard
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainAccent
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.Pink40
import com.example.viridis.ui.theme.SecondaryAccent
import com.example.viridis.ui.theme.ShadeColor
import com.example.viridis.ui.theme.WaterColor
import com.example.viridis.ui.theme.urbanistFont
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterial3Api
@Composable
fun addedPlantDetailScreen(
    navController: NavController,
    viewModel: AddedPlantDetailViewModel = viewModel(factory = AddedPlantDetailViewModel.Factory)
) {

    val plant by viewModel.plant.collectAsState()

    plant?.let { plant ->
        ImageHeaderScaffold(
            navController = navController,
            imageUrl = plant.imageUrl,
            imageHeight = 300.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = plant.name,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            style = TextStyle(
                                fontFamily = urbanistFont,
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        CustomIconButton(
                            icon = Icons.Filled.Delete,
                            onClick = {

                            },
                            containerColor = Pink40,
                            contentColor = Color.White,
                            modifier = Modifier
                                .width(80.dp)
                                .height(38.dp)
                        )
                    }
                    Text(
                        text = plant.scientificName,
                        color = MainColor,
                        fontSize = 16.sp,
                        style = TextStyle(
                            fontFamily = urbanistFont,
                        )
                    )
                }

                InfoCard(
                    title = "You last watered your plant on " + plant.lastWatered,
                    subtitle = "Watering scheduled " + plant.watering,
                    containerColor = SecondaryAccent,
                    textColor = BackgroundColor,
                    imageVector = Icons.Filled.WaterDrop
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoCard(
                        title = "Watering",
                        subtitle = "Next watering in " + plant.watering,
                        modifier = Modifier.weight(1f),
                        containerColor = WaterColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.Shower
                    )
                    InfoCard(
                        title = "Sunlight",
                        subtitle = plant.shadeLevel,
                        modifier = Modifier.weight(1f),
                        containerColor = ShadeColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.WbSunny,
                        cardPadding = 24.dp
                    )
                }

                InfoCard(
                    title = "Watering Streak",
                    subtitle = "You have a " + plant.streak + " days streak",
                    containerColor = MainAccent,
                    textColor = MainColor,
                    imageVector = Icons.Filled.LocalFireDepartment
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SecondaryAccent, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Task,
                            contentDescription = "Task",
                            tint = BackgroundColor,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            "Recommendations",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BackgroundColor,
                            style = TextStyle(
                                fontFamily = urbanistFont
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)

                    ) {
                        plant.recommendations.forEach { recommendation ->
                            Text(
                                text = recommendation,
                                modifier = Modifier
                                    .background(BackgroundColor, shape = RoundedCornerShape(16.dp))
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                style = TextStyle(
                                    fontFamily = urbanistFont,
                                    fontSize = 12.sp,
                                    color = SecondaryAccent
                                )
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
