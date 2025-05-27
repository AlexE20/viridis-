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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.viridis.Navigation.Creation
import com.example.viridis.Navigation.LogIn
import com.example.viridis.Navigation.SearchPlant
import com.example.viridis.data.viewModel.GardenViewModel
import com.example.viridis.data.viewModel.PlantViewModel
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.components.buttons.CustomIconButton
import com.example.viridis.ui.components.buttons.CustomIconTextButton
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainAccent
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.Pink40
import com.example.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import com.example.viridis.ui.components.cards.CustomCard


@Composable
fun gardenContentScreen(navController : NavController,gardenId:Int){
    val viewModel: PlantViewModel=viewModel()

    LaunchedEffect(gardenId) {
        viewModel.loadPlantsByGarden(gardenId)
    }

    val plants by viewModel.plants.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MainColor
                )
            }

            Spacer(modifier = Modifier.height(1.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(BackgroundColor),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Daniel's Studio",
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
                            .width(190.dp)
                            .height(42.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomIconButton(
                        icon = Icons.Filled.DeleteOutline,
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

                LazyColumn {
                    println("plants:${plants.size}")
                }

                }
            }
        }
    }

