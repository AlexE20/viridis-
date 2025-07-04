package com.pdm.viridis.ui.screens.gardenCreation.gardenName

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdm.viridis.ui.components.layouts.CustomTopBar
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.textfields.ProfileTextfield
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun GardenNameScreen(
    viewModel : GardenNameViewModel,
    onNext : (String) -> Unit
){
    CustomTopBar(
    ) {

        val gardenName by viewModel.gNameText.collectAsState()

        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(26.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = "Let´s name your new Garden!",
                fontSize = 32.sp,
                color = MainColor,
                lineHeight = 40.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Assign gardens for different parts of your house! For example, Kitchen, Porsh, Terrace etc.",
                fontSize = 15.sp,
                color = MainColor,
                lineHeight = 20.sp
            )
        }
        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ProfileTextfield(
                value = gardenName,
                onValueChange = { viewModel.onNameChanged(it) },
                placeholder = "Name your garden",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit icon",
                        tint = SecondaryAccent,
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(SecondaryAccent)
            )

        }

         Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
             CustomButton("Next",
                 onClick = { onNext(viewModel.gNameText.value) },
                 enabled =  viewModel.isValid(),
                 modifier = Modifier.width(351.dp).height(51.dp)

             )
        }
    }
}