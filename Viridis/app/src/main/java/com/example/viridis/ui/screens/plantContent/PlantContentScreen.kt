package com.example.viridis.ui.screens.plantContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viridis.ui.components.layouts.ImageHeaderScaffold
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.components.cards.DetailCardStacked
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun PlantContentScreen(
    navController: NavController,
    plantId : String,
    viewModel : PlantContentViewModel = viewModel()
) {

    val plantState = viewModel.plant.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPlantById(plantId)
    }

    val plant = plantState.value
    if (plant != null) {
        ImageHeaderScaffold(navController, 3) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            plant.name,
                            fontFamily = urbanistFont,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor
                        )
                        Spacer(Modifier.padding(4.dp))
                        Text(
                            plant.scientificName,
                            fontFamily = urbanistFont,
                            fontSize = 16.sp,
                            color = MainColor
                        )
                    }
                    Box() {
                        Text(
                            plant.difficulty,
                            fontFamily = urbanistFont,
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.padding(8.dp))
                    Box() {
                        val lightIcon = when (plant.shadeLevel) {
                            "full_shade" -> Icons.Outlined.DarkMode
                            "part_shade" -> Icons.Outlined.WbCloudy
                            "sun-part_shade" -> Icons.Filled.WbCloudy
                            "full_sun" -> Icons.Filled.WbSunny
                            else -> Icons.Filled.WbSunny
                        }
                        Icon(
                            imageVector = lightIcon,
                            contentDescription = null,
                            tint = BackgroundColor,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(Modifier.padding(20.dp))
                DetailCardStacked(
                    plant.shadeLevel,
                    plant.watering,
                    plant.recommendations
                )
                Spacer(Modifier.padding(20.dp))
                CustomButton("Add Plant to Garden", {})
            }
        }
    } else {
        //something like an error
    }
}

