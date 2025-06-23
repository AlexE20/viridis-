package com.pdm.viridis.ui.screens.plantContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.cards.DetailCardStacked
import com.pdm.viridis.ui.components.layouts.ImageHeaderScaffold
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun PlantContentScreen(
    navController: NavController,
    viewModel: PlantContentViewModel = viewModel(factory = PlantContentViewModel.Factory)
) {
    val plant by viewModel.plant.collectAsStateWithLifecycle()

    plant?.let { pl ->
        ImageHeaderScaffold(
            navController = navController,
            imageUrl = pl.imageUrl,
            imageHeight = 300.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = pl.name ?: "Unknown",
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            fontFamily = urbanistFont
                        )
                        Text(
                            text = pl.scientificName ?: "",
                            fontSize = 16.sp,
                            color = MainColor,
                            fontFamily = urbanistFont
                        )
                    }
                    Card(
                        modifier = Modifier.height(45.dp).width(76.dp),
                        colors = CardDefaults.cardColors(SecondaryAccent)
                    ) {
                        Box(Modifier.fillMaxSize(), Alignment.Center) {
                            Text(
                                text = pl.careLevel ?: "-",
                                fontSize = 16.sp,
                                color = BackgroundColor,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = urbanistFont
                            )
                        }
                    }

                    Spacer(Modifier.width(8.dp))

                    val lightIcon = when (pl.shadeLevel) {
                        "full_shade"      -> Icons.Outlined.DarkMode
                        "part_shade"      -> Icons.Outlined.WbCloudy
                        "sun-part_shade"  -> Icons.Filled.WbCloudy
                        "full_sun"        -> Icons.Filled.WbSunny
                        else              -> Icons.Filled.WbSunny
                    }

                    Card(
                        modifier = Modifier.height(45.dp).width(76.dp),
                        colors = CardDefaults.cardColors(SecondaryAccent)
                    ) {
                        Box(Modifier.fillMaxSize(), Alignment.Center) {
                            Icon(
                                imageVector = lightIcon,
                                contentDescription = pl.shadeLevel,
                                tint = BackgroundColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                DetailCardStacked(
                    lightOption      = pl.shadeLevel ?: "-",
                    wateringOption   = pl.watering    ?: "-",
                    recommendations = pl.recommendations?.map { it.description } ?: emptyList()
                )

                Spacer(Modifier.height(20.dp))

                CustomButton(text = "Add Plant to Garden", onClick = { /* TODO */ })
            }
        }
    } ?: Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading...", fontFamily = urbanistFont, color = MainColor)
    }
}


