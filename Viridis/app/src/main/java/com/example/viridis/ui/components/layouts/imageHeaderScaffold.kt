package com.example.viridis.ui.components.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor

@ExperimentalMaterial3Api
@Composable
fun ImageHeaderScaffold(
    navController: NavController,
    imageRes: Int,
    imageHeight: Dp = 220.dp,
    floatingActionButton: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 64.dp))
                )

                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .size(45.dp)
                                .background(BackgroundColor, shape = CircleShape)
                                .align(Alignment.TopStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MainColor,
                                modifier = Modifier.size(27.dp)
                            )
                        }
                    }
                )


            }
        },
        floatingActionButton = {
            floatingActionButton?.invoke()
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = BackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            content()
        }
    }
}
