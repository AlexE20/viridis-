package com.pdm.viridis.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.pdm.viridis.Navigation.Creation
import com.pdm.viridis.ui.components.layouts.CustomScaffold
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.cards.StakedCards
import com.pdm.viridis.ui.theme.BackgroundColor
import androidx.compose.runtime.getValue
import com.pdm.viridis.ui.theme.MainColor
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.GardenContentScreen
import com.pdm.viridis.Navigation.GardenNameScreen
import com.pdm.viridis.ui.theme.urbanistFont
import com.pdm.viridis.utils.NetworkUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val gardens by viewModel.gardens.collectAsState()
    val imageUrlMap by viewModel.imageUrlsMap.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val isLoading = gardens.isEmpty()
    val context = LocalContext.current
    val isConnected = NetworkUtils.isConnected(context)

    LaunchedEffect(Unit) {
        viewModel.loadGardens(isConnected)
    }

    CustomScaffold{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hello There!",
                fontSize = 32.sp,
                color = MainColor,
                lineHeight = 40.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We are glad you are here. Let’s pay your gardens a visit, collect information and watch your plants thrive!",
                fontSize = 15.sp,
                color = MainColor,
                lineHeight = 20.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                text = "Add Garden",
                onClick = {navigator.push(GardenNameScreen)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if(isLoading){
                CircularProgressIndicator(
                    color = MainColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(gardens) { garden ->
                        val urls = imageUrlMap[garden.id] ?: emptyList()

                        StakedCards(
                            clickable = { navigator.push(GardenContentScreen(garden.id, garden.name))},
                            gardenName = garden.name,
                            gardenShade = garden.shadeLevel,
                            imageUrls = urls,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 160.dp)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}