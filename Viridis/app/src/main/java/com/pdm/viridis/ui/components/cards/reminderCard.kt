package com.pdm.viridis.ui.components.cards

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Shower
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.ThirdAccent
import com.pdm.viridis.ui.theme.WarningColor
import com.pdm.viridis.ui.theme.WaterColor
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun ReminderCard(
    plantName: String,
    location: String,
    dueDate: String,
    imagePainter: Painter,
    isDue: Boolean,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val accentColor = MainColor
    val dueTextColor = if (isDue) accentColor else WarningColor

    val backgroundColor by animateColorAsState(
        targetValue = if (isChecked) WaterColor else ThirdAccent,
        animationSpec = tween(500)
    )
    val checkboxScale by animateFloatAsState(
        targetValue = if (isChecked) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(backgroundColor)
            .padding(18.dp)
            .padding(vertical = 20.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Shower,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(44.dp)
            )
            Spacer(Modifier.width(8.dp))

            Text(
                text = "Reminder",
                color = accentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                style = TextStyle(
                    fontFamily = urbanistFont
                )
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "Due to $dueDate",
                color = dueTextColor,
                fontSize = 16.sp,
                style = TextStyle(
                    fontFamily = urbanistFont
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = plantName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = accentColor,
                    style = TextStyle(
                        fontFamily = urbanistFont
                    )
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = location,
                    color = accentColor,
                    fontSize = 15.sp,
                    style = TextStyle(
                        fontFamily = urbanistFont
                    )
                )
            }

            Spacer(Modifier.weight(1f))

            Box(Modifier.scale(checkboxScale)) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = accentColor,
                        checkmarkColor = WaterColor,
                        uncheckedColor = accentColor
                    )
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ReminderCardPreview() {
//    val checked = remember { mutableStateOf(false) }
//    ReminderCard(
//        plantName = "Aloe Vera",
//        location = "Daniel's Studio",
//        dueDate = "8/5/2024",
//        imagePainter = painterResource(R.drawable.login_header_image),
//        isDue = true,
//        isChecked = checked.value,
//        onCheckedChange = { checked.value = it }
//    )
//}
