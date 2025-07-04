package com.pdm.viridis.ui.screens.searchPlant

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.ui.graphics.vector.ImageVector

fun shadeIconFor(level: String?): ImageVector = when (level) {
    "full_shade"      -> Icons.Outlined.DarkMode
    "part_shade"      -> Icons.Outlined.Cloud
    "sun-part_shade"  -> Icons.Filled.WbCloudy
    "full_sun"        -> Icons.Filled.WbSunny
    else              -> Icons.Filled.WbSunny
}
