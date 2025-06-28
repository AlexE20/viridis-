package com.pdm.viridis.ui.screens.addedPlantDetail

import android.R.attr.subtitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.ui.components.layouts.ImageHeaderScaffold
import com.pdm.viridis.ui.screens.plantContent.PlantContentViewModel
import com.pdm.viridis.ui.theme.MainColor
import androidx.compose.ui.text.TextStyle
import com.pdm.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.layout.Spacer
import com.pdm.viridis.ui.components.buttons.CustomIconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.pdm.viridis.ui.theme.Pink40
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import com.pdm.viridis.ui.components.cards.InfoCard
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainAccent
import com.pdm.viridis.ui.theme.ShadeColor
import com.pdm.viridis.ui.theme.WaterColor
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.ui.components.BottomSheets.AlertBottomSheet
import com.pdm.viridis.ui.components.BottomSheets.BottomAlertSheet

@ExperimentalMaterial3Api
@Composable
fun addedPlantDetailScreen(
    viewModel: AddedPlantDetailViewModel,
    id: String,
    gardenId: String,
    commonName:String,
    scientificName:String,
    careLevel:String,
    shadeLevel:String,
    watering:String,
    defaultImage:String,
    recommendations: List<Recommendation>,
    wateredStreak:Int,
    lastWateredAt:String
) {
    val navigator = LocalNavigator.currentOrThrow
    val showSuccessSheet by viewModel.showSuccessSheet.collectAsState()
    val showDeleteConfirmation by viewModel.showDeleteConfirmation.collectAsState()

    if (showDeleteConfirmation) {
        BottomAlertSheet(
            message = "This plant will be gone forever. Are you sure you want to say goodbye?",
            buttonText = "Delete",
            onButtonClick = { viewModel.deletePlant(id) },
            onDismiss = { viewModel.cancelDelete() }
        )
    }

    if (showSuccessSheet) {
        AlertBottomSheet(
            icon = Icons.Default.CheckCircle,
            message = "Your Garden Was Deleted",
            onDismiss = { viewModel.dismissSuccessSheet() },
            color = SecondaryAccent,
            onContentClick = {
                viewModel.dismissSuccessSheet()
                navigator.push(HomeScreen)
            }
        )
    }

    var wateringV=watering
    if(watering=="Average"){
        wateringV= "1 day"
    }else{
        wateringV="2 days"
    }

    ImageHeaderScaffold(
            imageUrl = defaultImage,
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
                            modifier = Modifier.weight(1f),
                            text = commonName,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            style = TextStyle(
                                fontFamily = urbanistFont,
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        CustomIconButton(
                            icon = Icons.Filled.Delete,
                            onClick = {
                                viewModel.showDeleteConfirmation()
                            },
                            containerColor = Pink40,
                            contentColor = Color.White,
                            modifier = Modifier
                                .width(80.dp)
                                .height(38.dp)
                        )
                    }
                    Text(
                        text = scientificName,
                        color = MainColor,
                        fontSize = 16.sp,
                        style = TextStyle(
                            fontFamily = urbanistFont,
                        )
                    )
                }

                InfoCard(
                    title = "You last watered your plant on $lastWateredAt",
                    subtitle = "Watering scheduled $watering",
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
                        subtitle = "Every $wateringV",
                        modifier = Modifier.weight(1f),
                        containerColor = WaterColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.Shower
                    )

                    InfoCard(
                        title = "Sunlight",
                        subtitle = shadeLevel,
                        modifier = Modifier.weight(1f),
                        containerColor = ShadeColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.WbSunny,
                        cardPadding = 24.dp
                    )
                }

                InfoCard(
                    title = "Watering Streak",
                    subtitle = "You have a $wateredStreak days streak",
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
                        recommendations.forEach { recommendation ->
                            Text(
                                text = recommendation.description,
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
