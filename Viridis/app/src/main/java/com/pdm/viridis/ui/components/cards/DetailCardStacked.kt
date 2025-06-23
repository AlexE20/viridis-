package com.pdm.viridis.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.data.model.Recommendation
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont


@Composable
fun DetailCardStacked(
    lightOption: String,
    wateringOption: String,
    recommendations: List<Recommendation>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryAccent, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Info",
            color = BackgroundColor,
            fontSize = 32.sp,
            fontFamily = urbanistFont
        )

        val lightIcon = when (lightOption) {
            "full_shade" -> Icons.Outlined.DarkMode
            "part_shade" -> Icons.Outlined.WbCloudy
            "sun-part_shade" -> Icons.Filled.WbCloudy
            "full_sun" -> Icons.Filled.WbSunny
            else -> Icons.Filled.WbSunny
        }

        DetailCard(
            icon = lightIcon,
            title = "Sunlight",
            description = lightOption
        )

        DetailCard(
            icon = Icons.Filled.Shower,
            title = "Watering",
            description = wateringOption
        )

        if (recommendations.isEmpty()) {
            DetailCard(
                icon = Icons.Filled.HighlightOff,
                title = "Oh no!",
                description = "This little plant doesn’t have any tips yet. Still lovely though!"
            )
        } else {
            recommendations.forEach { recommendation ->
                val title = when (recommendation.type.lowercase()) {
                    "watering" -> "Watering Advice"
                    "sunlight" -> "Sunlight Advice"
                    "pruning" -> "Pruning Tips"
                    else -> recommendation.type.replaceFirstChar { it.uppercaseChar() }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = BackgroundColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = title,
                            color = SecondaryAccent,
                            fontFamily = urbanistFont,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = recommendation.description,
                            color = SecondaryAccent.copy(alpha = 0.8f),
                            fontFamily = urbanistFont,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

