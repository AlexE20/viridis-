package com.pdm.viridis.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun InfoCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    containerColor: Color,
    textColor: Color,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    cardPadding: Dp = 16.dp,
    iconSize: Dp = 46.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(containerColor)
            .padding(cardPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            painter != null -> Icon(painter = painter, contentDescription = null, tint = textColor, modifier = Modifier.size(iconSize))
            imageVector != null -> Icon(imageVector = imageVector, contentDescription = null, tint = textColor, modifier = Modifier.size(iconSize))
        }

        if (painter != null || imageVector != null) {
            Spacer(modifier = Modifier.width(6.dp))
        }

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                style = TextStyle(fontFamily = urbanistFont)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontFamily = urbanistFont,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}
