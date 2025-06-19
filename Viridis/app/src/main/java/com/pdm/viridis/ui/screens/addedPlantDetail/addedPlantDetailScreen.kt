package com.pdm.viridis.ui.screens.addedPlantDetail

/*
@ExperimentalMaterial3Api
@Composable
fun addedPlantDetailScreen(
    navController: NavController,
    viewModel: AddedPlantDetailViewModel = viewModel(factory = AddedPlantDetailViewModel.Factory)
) {

    val plant by viewModel.plant.collectAsState()

    plant?.let { plant ->
        ImageHeaderScaffold(
            navController = navController,
            imageUrl = plant.imageUrl,
            imageHeight = 300.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = plant.name,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            style = TextStyle(
                                fontFamily = urbanistFont,
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        CustomIconButton(
                            icon = Icons.Filled.Delete,
                            onClick = {

                                /* viewModel.deletePlant()
                                navController.popBackStack() */
                            },
                            containerColor = Pink40,
                            contentColor = Color.White,
                            modifier = Modifier
                                .width(80.dp)
                                .height(38.dp)
                        )
                    }
                    Text(
                        text = plant.scientificName,
                        color = MainColor,
                        fontSize = 16.sp,
                        style = TextStyle(
                            fontFamily = urbanistFont,
                        )
                    )
                }

                InfoCard(
                    title = "You last watered your plant on " + plant.lastWatered,
                    subtitle = "Watering scheduled " + plant.watering,
                    containerColor = SecondaryAccent,
                    textColor = BackgroundColor,
                    imageVector = Icons.Filled.WaterDrop
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoCard(
                        title = "Watering",
                        subtitle = "Next watering in " + plant.watering,
                        modifier = Modifier.weight(1f),
                        containerColor = WaterColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.Shower
                    )
                    InfoCard(
                        title = "Sunlight",
                        subtitle = plant.shadeLevel,
                        modifier = Modifier.weight(1f),
                        containerColor = ShadeColor,
                        textColor = BackgroundColor,
                        imageVector = Icons.Filled.WbSunny,
                        cardPadding = 24.dp
                    )
                }

                InfoCard(
                    title = "Watering Streak",
                    subtitle = "You have a " + plant.streak + " days streak",
                    containerColor = MainAccent,
                    textColor = MainColor,
                    imageVector = Icons.Filled.LocalFireDepartment
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SecondaryAccent, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Task,
                            contentDescription = "Task",
                            tint = BackgroundColor,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            "Recommendations",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BackgroundColor,
                            style = TextStyle(
                                fontFamily = urbanistFont
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)

                    ) {
                        plant.recommendations.forEach { recommendation ->
                            Text(
                                text = recommendation,
                                modifier = Modifier
                                    .background(BackgroundColor, shape = RoundedCornerShape(16.dp))
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                style = TextStyle(
                                    fontFamily = urbanistFont,
                                    fontSize = 12.sp,
                                    color = SecondaryAccent
                                )
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
*/