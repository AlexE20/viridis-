package com.pdm.viridis.ui.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun InteractiveText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MainColor,
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
            fontFamily = urbanistFont,
            textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
        )
    }
    //A text with onClick action and probably navigation
}
