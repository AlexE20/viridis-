package com.example.viridis.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.SecondaryAccent
import com.example.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.rememberScrollState


@Composable
fun DetailCardStacked(
    lightOption: String,
    wateringOption: String,
    recommendations: List<String>
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

        Spacer(modifier = Modifier.height(4.dp))

        recommendations.forEach { recommendation ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = BackgroundColor)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = recommendation,
                        color = SecondaryAccent,
                        fontFamily = urbanistFont,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

