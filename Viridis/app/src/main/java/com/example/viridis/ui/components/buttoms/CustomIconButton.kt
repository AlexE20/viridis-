package com.example.viridis.ui.components.buttoms

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

//A icon only that will have the style based on the figma
@Composable
fun CustomIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    //These are default colors, but can be change when you call the function
    containerColor: Color = Color(0xFFBEE698),
    contentColor: Color = Color(0xFFF9FBE7)
){

    Button(
        onClick = onClick,
        modifier = Modifier.width(80.dp).height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "icon"
        )
    }
}