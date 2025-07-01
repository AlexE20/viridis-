package com.pdm.viridis.ui.screens.aboutUs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.ui.components.cards.AboutCard
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont


@ExperimentalMaterial3Api
@Composable
fun AboutUsScreen(){
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
                text = "About Viridis",
                fontSize = 30.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )

            Spacer(modifier = Modifier.height(48.dp))

            AboutCard("Credits", "Lorem Ipsum is simply dummy text of the printing and  typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of  type and scrambled it to make a type specimen book.")

            Spacer(modifier = Modifier.height(24.dp))

            AboutCard("About Us", "Lorem Ipsum is simply dummy text of the printing and  typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of  type and scrambled it to make a type specimen book.")

        }
    }
}