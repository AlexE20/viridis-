package com.pdm.viridis.ui.screens.searchPlant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavController
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.PlantContentScreen
import com.pdm.viridis.ui.components.buttons.CustomIconButton
import com.pdm.viridis.ui.components.cards.CustomCard
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.components.textfields.ProfileTextfield
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont
import com.pdm.viridis.Navigation.SearchPlantScreen


@ExperimentalMaterial3Api
@Composable
fun SearchPlantScreen(
    viewModel: PlantSearchViewModel,
    gardenId: String,
) {
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val filteredPlants by viewModel.plants.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val searchStarted by viewModel.searchStarted.collectAsStateWithLifecycle()
    val navigator= LocalNavigator.currentOrThrow

    CustomTopBar() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Add plant",
                fontSize = 30.sp,
                fontFamily = urbanistFont,
                color = MainColor,
                fontWeight = FontWeight.Bold

            )
            Spacer(Modifier.padding(8.dp))
            Text(
                text = "You can look for the plant you wish to add to your garden, or scan one you already have!",
                fontSize = 16.sp,
                fontFamily = urbanistFont,
                color = MainColor
            )
            Spacer(Modifier.padding(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileTextfield(
                    value = searchText,
                    onValueChange = viewModel::onSearchTextChange,
                    placeholder = "Search Plants",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = SecondaryAccent,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier.height(56.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                CustomIconButton(
                    Icons.Filled.Search,
                    onClick = {
                        if (searchText.isNotBlank()) {
                            viewModel.searchPlants()
                            viewModel.onSearchStarted()
                        }
                    },
                    modifier = Modifier.height(56.dp),
                    containerColor = SecondaryAccent
                )
            }

            Spacer(Modifier.padding(16.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    color = MainColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else if (searchStarted && filteredPlants.isEmpty()) {
                Text("Nothing here, try a different plant!", color = MainColor, modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(filteredPlants) { plant ->
                        val icon = shadeIconFor(plant.shadeLevel)
                        CustomCard(
                            plantName = plant.name ?: "",
                            plantDescription = plant.scientificName ?: "",
                            plantImgUrl = plant.imageUrl ?: "",
                            difficulty = plant.careLevel ?: "",
                            difficultyIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = plant.shadeLevel,
                                    tint = SecondaryAccent,
                                    modifier = Modifier
                                        .background(
                                            BackgroundColor,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(6.dp)
                                        .size(18.dp)
                                )
                            },
                            clickable = {
                                println("RECOMMENDATIONS: ${plant.recommendations?.joinToString { it.type }}")
                                println("🌿 PLANT ID: ${plant.id}")

                                navigator.push(
                                    PlantContentScreen(
                                        plantId = plant.id.orEmpty(),
                                        gardenId=gardenId,
                                        commonName = plant.name.orEmpty(),
                                        scientificName = plant.scientificName.orEmpty(),
                                        careLevel = plant.careLevel.orEmpty(),
                                        shadeLevel = plant.shadeLevel.orEmpty(),
                                        watering = plant.watering.orEmpty(),
                                        recommendations = plant.recommendations ?: emptyList(),
                                        imageUrl = plant.imageUrl.orEmpty()
                                    )
                                )
                            }
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}