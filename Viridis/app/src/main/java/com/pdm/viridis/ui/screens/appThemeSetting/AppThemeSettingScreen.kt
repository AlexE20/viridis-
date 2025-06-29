package com.pdm.viridis.ui.screens.appThemeSetting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.components.buttons.CustomRadioButton
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont
import kotlin.collections.component1
import kotlin.collections.component2

@ExperimentalMaterial3Api
@Composable
fun AppThemeSettingScreen(){

    val options = listOf<String>(
        "Light Mode",
        "Dark Mode"
    )

    val icons = listOf(
        Icons.Filled.WbSunny,
        Icons.Outlined.DarkMode
    )

    CustomTopBar {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ){
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "App Theme",
                fontSize = 30.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .background(BackgroundColor)
                    .padding(26.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /*
                options.forEachIndexed { index, label ->
                    CustomRadioButton(
                        selected = selected == label,
                        text = label,
                        icon = icons[index],
                        onClick = {  }
                    )
                }
                */
            }
        }
    }
}