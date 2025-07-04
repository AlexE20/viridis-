package com.pdm.viridis.ui.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.MainColor
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun CustomButton(
    text:String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: androidx.compose.ui.graphics.Color = MainColor,
    textColor: androidx.compose.ui.graphics.Color = BackgroundColor,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(377.dp)
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            fontFamily = urbanistFont
        )
    }
}
