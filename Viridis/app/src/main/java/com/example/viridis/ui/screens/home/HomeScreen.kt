package com.example.viridis.ui.screens.home

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
import androidx.navigation.NavHostController
import com.example.viridis.Navigation.Creation
import com.example.viridis.ui.components.layouts.CustomScaffold
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.components.cards.StakedCards
import com.example.viridis.ui.theme.BackgroundColor
import androidx.compose.runtime.getValue
import com.example.viridis.ui.theme.MainColor
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.font.FontWeight
import com.example.viridis.ui.theme.urbanistFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val gardens by viewModel.gardens.collectAsState()

    CustomScaffold(navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(16.dp),
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
                onClick = { navController.navigate(Creation) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(gardens) { garden ->
                    StakedCards(
                        clickable = { navController.navigate("gardenContent/${garden.id}/${garden.name}")},
                        gardenName = garden.name,
                        gardenShade = garden.shadeLevel,
                        imageUrls = null,
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