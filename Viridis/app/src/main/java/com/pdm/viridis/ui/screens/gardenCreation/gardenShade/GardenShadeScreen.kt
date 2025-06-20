package com.pdm.viridis.ui.screens.gardenCreation.gardenShade

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pdm.viridis.Navigation.Home
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.components.buttons.CustomRadioButton
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun GardenShadeScreen(navController: NavController){

    val options = listOf("Full shade", "Part shade", "Sun part shade", "Full sun")
    val icons = listOf(Icons.Outlined.DarkMode, Icons.Outlined.Cloud, Icons.Filled.WbCloudy, Icons.Filled.WbSunny)
    var selected by remember { mutableStateOf(options[0]) }

    CustomTopBar(
        navController = navController
    ) {
        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(26.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Select the shade level of your Garden!",
                fontSize = 32.sp,
                color = MainColor,
                lineHeight = 40.sp,
                style = TextStyle(
                    fontFamily = urbanistFont
                )
            )

            Spacer(modifier = Modifier.height(14.dp))


            Text(
                text = "Which type of light best suits your garden’s location?",
                fontSize = 16.sp,
                color = MainColor,
                lineHeight = 20.sp,
                style = TextStyle(
                    fontFamily = urbanistFont
                )
            )
        }
        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(26.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            options.forEachIndexed { index, option ->
                CustomRadioButton(
                    selected = selected == option,
                    text = option,
                    icon = icons[index],
                    onClick = { selected = option }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton("Save",
                onClick = {navController.navigate(Home)},
                modifier = Modifier.width(351.dp).height(51.dp)
            )
        }
    }
}