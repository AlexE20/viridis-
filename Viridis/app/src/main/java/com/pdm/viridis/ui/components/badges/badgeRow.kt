package com.pdm.viridis.ui.components.badges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BadgeRow(activeStreaks: Int) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BadgeItem(icon = Icons.Default.Cloud, name = "Agriculturer", requiredStreak = 10, activeStreaks = activeStreaks)
        BadgeItem(icon = Icons.Default.Star, name = "Botanist", requiredStreak = 20, activeStreaks = activeStreaks)
        BadgeItem(icon = Icons.Default.LocalFlorist, name = "Grower", requiredStreak = 30, activeStreaks = activeStreaks)
    }
}
