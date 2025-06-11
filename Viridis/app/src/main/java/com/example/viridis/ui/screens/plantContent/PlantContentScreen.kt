package com.example.viridis.ui.screens.plantContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.viridis.Navigation.Home
import com.example.viridis.Navigation.SignUp
import com.example.viridis.R
import com.example.viridis.ui.components.ImageHeaderScaffold
import com.example.viridis.ui.components.buttons.CustomButton
import com.example.viridis.ui.components.textfields.AuthTextField
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor
import com.example.viridis.ui.theme.urbanistFont
 /*
@ExperimentalMaterial3Api
@Composable
fun PlantContentScreen(navController: NavController){

     ImageHeaderScaffold(
        navController = navController
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp)
                .background(BackgroundColor),
            horizontalAlignment = Alignment.Start
        ){
            Row(){
                Column() {
                    Text("")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("")
                }
                Card() {
                    Box(){
                        Text("")
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Card() {
                    Box() {
                        //Icon()
                    }
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Card() {
                Column() {
                    Text("")
                    Card() {
                        Row() {
                            //Icon()
                            Column {
                                Text("")
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text("")
                            }
                        }
                    }
                    Card() {
                        Row() {
                            //Icon()
                            Column {
                                Text("")
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text("")
                            }
                        }
                    }
                    Card() {
                        Box(){
                            Text("")
                        }
                    }
                }
            }
        }
    }
} */