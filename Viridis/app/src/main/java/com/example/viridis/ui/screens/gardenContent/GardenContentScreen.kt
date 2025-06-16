package com.example.viridis.ui.screens.gardenContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viridis.Navigation.SearchPlant
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.components.buttons.CustomIconButton
import com.example.viridis.ui.components.buttons.CustomIconTextButton
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainAccent
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.Pink40
import com.example.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.viridis.Navigation.addedPlantDetail
import com.example.viridis.ui.components.layouts.CustomTopBar
import com.example.viridis.ui.components.cards.CustomCard
import com.example.viridis.ui.theme.SecondaryAccent

@ExperimentalMaterial3Api
@Composable
fun GardenContentScreen(
    navController: NavController,
    viewModel: GardenContentViewModel = viewModel(factory = GardenContentViewModel.Factory)
) {
    val plants by viewModel.plants.collectAsState()
    val gardenName = viewModel.getGardenName()

    CustomTopBar(navController = navController)
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(BackgroundColor),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = gardenName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor,
                    style = TextStyle(
                        fontFamily = urbanistFont,
                        fontSize = 30.sp,
                        color = MainColor
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomIconTextButton(
                        onClick = { /*Logica para marcar como favorito*/ },
                        text = "Mark as favorite",
                        imageVector = Icons.Filled.FavoriteBorder,
                        containerColor = MainAccent,
                        contentColor = MainColor,
                        modifier = Modifier
                            .width(216.dp)
                            .height(42.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomIconButton(
                        icon = Icons.Filled.Delete,
                        onClick = { /* LOgica de liminar jardín */ },
                        containerColor = Pink40,
                        contentColor = Color.White,
                        modifier = Modifier
                            .width(80.dp)
                            .height(42.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomButton(
                    text = "Add Plant",
                    onClick = { navController.navigate(SearchPlant) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(plants) { plant ->
                        CustomCard(
                            clickable = {
                                if (plant.id.isNotBlank()) {
                                    navController.navigate("addedPlantDetail/${plant.id}")
                                }
                            },
                            plantName = plant.name,
                            plantDescription = plant.scientificName,
                            plantImgUrl = plant.imageUrl,
                            difficulty = plant.difficulty,
                            difficultyIcon = {
                                Icon(
                                    imageVector = Icons.Filled.WbSunny,
                                    contentDescription = null,
                                    tint = SecondaryAccent,
                                    modifier = Modifier
                                        .background(
                                            BackgroundColor,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(6.dp)
                                        .size(18.dp)
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
            }
        }
    }
}

