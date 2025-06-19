package com.pdm.viridis.ui.screens.plantContent

/*@ExperimentalMaterial3Api
@Composable
fun PlantContentScreen(
    navController: NavController,
    viewModel: PlantContentViewModel = viewModel(factory = PlantContentViewModel.Factory)
) {

    val plantState = viewModel.plant.collectAsState()
    val plant = plantState.value

    if (plant != null) {
        ImageHeaderScaffold(
            navController = navController,
            imageUrl = plant.imageUrl,
            imageHeight = 300.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // 👈 Habilita scroll
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Encabezado: nombre y controles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = plant.name,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            fontFamily = urbanistFont
                        )
                        Text(
                            text = plant.scientificName,
                            fontSize = 16.sp,
                            color = MainColor,
                            fontFamily = urbanistFont
                        )
                    }

                    Card(
                        modifier = Modifier
                            .height(45.dp)
                            .width(76.dp),
                        colors = CardDefaults.cardColors(SecondaryAccent)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                plant.difficulty,
                                fontSize = 16.sp,
                                color = BackgroundColor,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = urbanistFont
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    val lightIcon = when (plant.shadeLevel) {
                        "full_shade" -> Icons.Outlined.DarkMode
                        "part_shade" -> Icons.Outlined.WbCloudy
                        "sun-part_shade" -> Icons.Filled.WbCloudy
                        "full_sun" -> Icons.Filled.WbSunny
                        else -> Icons.Filled.WbSunny
                    }

                    Card(
                        modifier = Modifier
                            .height(45.dp)
                            .width(76.dp),
                        colors = CardDefaults.cardColors(SecondaryAccent)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = lightIcon,
                                contentDescription = null,
                                tint = BackgroundColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                DetailCardStacked(
                    lightOption = plant.shadeLevel,
                    wateringOption = plant.watering,
                    recommendations = plant.recommendations
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomButton("Add Plant to Garden", {})
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", fontFamily = urbanistFont, color = MainColor)
        }
    }
}*/

