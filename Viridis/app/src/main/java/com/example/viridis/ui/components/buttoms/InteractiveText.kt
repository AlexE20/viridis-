package com.example.viridis.ui.components.buttoms

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun InteractiveText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color(4278274374),
    underline: Boolean = false
){
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(containerColor = Color.Transparent, contentColor = textColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text= text,
            textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
        )
    }
    //A text with onClick action and probably navigation
}
