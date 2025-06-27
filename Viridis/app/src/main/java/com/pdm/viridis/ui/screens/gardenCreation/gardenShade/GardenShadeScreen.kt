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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.ui.components.BottomSheets.AlertBottomSheet
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.components.buttons.CustomRadioButton
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun GardenShadeScreen(
    viewModel: GardenShadeViewModel,
    gardenName: String
){

    val options = viewModel.shadeOptions.entries.toList()
    val selected by viewModel.selectedShade.collectAsState()
    val isSaving by viewModel.isSaving.collectAsState()
    val error by viewModel.error.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val showSuccessSheet by viewModel.showSuccessSheet.collectAsState()
    val scope = rememberCoroutineScope()

    val icons = listOf(
        Icons.Outlined.DarkMode,
        Icons.Outlined.Cloud,
        Icons.Filled.WbCloudy,
        Icons.Filled.WbSunny
    )

    if (showSuccessSheet) {
        AlertBottomSheet(
            icon = Icons.Default.CheckCircle,
            message = "Garden created successfully!",
            onDismiss = { viewModel.dismissSuccessSheet() },
            color = SecondaryAccent,
            onContentClick = {
                viewModel.dismissSuccessSheet()
                navigator.push(HomeScreen)
            }
        )
    }

    CustomTopBar{ snackbarHostSate ->
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
            options.forEachIndexed { index, (key, label) ->
                CustomRadioButton(
                    selected = selected == label,
                    text = label,
                    icon = icons[index],
                    onClick = { viewModel.onShadeSelected(label) }
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
                onClick = {
                    scope.launch {
                        viewModel.saveGarden(gardenName)
                    }
                },
                enabled = viewModel.isValid() && !isSaving,
                modifier = Modifier.width(351.dp).height(51.dp)
            )

            error?.let {

            }
        }
    }
}