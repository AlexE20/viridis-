package com.pdm.viridis.ui.screens.plantContent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.pdm.viridis.Navigation.GardenContentScreen
import com.pdm.viridis.Navigation.HomeScreen
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.ui.components.BottomSheets.AlertBottomSheet
import com.pdm.viridis.ui.components.buttons.CustomButton
import com.pdm.viridis.ui.components.cards.DetailCardStacked
import com.pdm.viridis.ui.components.layouts.ImageHeaderScaffold
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun PlantContentScreenUI(
	viewModel: PlantContentViewModel,
	gardenId: String,
	plantId:String,
	commonName: String,
	scientificName: String,
	careLevel: String,
	shadeLevel: String,
	watering: String,
	recommendations: List<Recommendation>,
	imageUrl: String
) {
	val navigator = LocalNavigator.currentOrThrow
	val showSuccessSheet by viewModel.showSuccessSheet.collectAsState()

	val lightIcon = when (shadeLevel) {
		"full_shade" -> Icons.Outlined.DarkMode
		"part_shade" -> Icons.Outlined.WbCloudy
		"sun-part_shade" -> Icons.Filled.WbCloudy
		"full_sun" -> Icons.Filled.WbSunny
		else -> Icons.Filled.WbSunny
	}

	if (showSuccessSheet) {
		AlertBottomSheet(
			icon = Icons.Default.CheckCircle,
			message = "Your Plant has been saved",
			onDismiss = { viewModel.dismissSuccessSheet() },
			color = SecondaryAccent,
			onContentClick = {
				viewModel.dismissSuccessSheet()
				navigator.replaceAll(HomeScreen)
				//navigator.push(GardenContentScreen(gardenId, gardenName))
			}
		)
	}

	ImageHeaderScaffold(
		imageUrl = imageUrl,
		imageHeight = 300.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.padding(horizontal = 24.dp, vertical = 16.dp),
			verticalArrangement = Arrangement.spacedBy(20.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(
					modifier = Modifier.weight(1f),
					verticalArrangement = Arrangement.spacedBy(4.dp)
				) {
					Text(
						text = commonName,
						fontSize = 33.sp,
						lineHeight = 38.sp,
						fontWeight = FontWeight.Bold,
						color = MainColor,
						fontFamily = urbanistFont
					)
					Text(
						text = scientificName,
						fontSize = 16.sp,
						color = MainColor,
						fontFamily = urbanistFont
					)
				}

				Card(
					modifier = Modifier.height(45.dp).width(88.dp),
					colors = CardDefaults.cardColors(SecondaryAccent)
				) {
					Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
						Text(
							careLevel,
							fontSize = 16.sp,
							color = BackgroundColor,
							fontWeight = FontWeight.SemiBold,
							fontFamily = urbanistFont
						)
					}
				}

				Spacer(modifier = Modifier.width(8.dp))

				Card(
					modifier = Modifier.height(45.dp).width(48.dp),
					colors = CardDefaults.cardColors(SecondaryAccent)
				) {
					Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
				lightOption = shadeLevel,
				wateringOption = watering,
				recommendations = recommendations
			)

			Spacer(modifier = Modifier.height(20.dp))
			CustomButton("Add Plant to Garden",
				onClick = {
				viewModel.savePlant(gardenId,plantId )
			})
		}
	}
}


