package com.example.viridis.ui.components.buttoms

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconTextButton(
    onClick: () -> Unit,
    text: String,
    icon: ImageVector,
    containerColor: Color = Color(4278274374),
    contentColor: Color = Color(0xFFF9FBE7),
    modifier: Modifier = Modifier,
){
    Button(
        onClick = onClick,
        modifier = Modifier.width(377.dp).height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor)
    ) {
        Icon(imageVector = icon, contentDescription = "icon")
        Text(text)
    }
    //A button that will have an icon and then the text, like google auth button or delete.
}