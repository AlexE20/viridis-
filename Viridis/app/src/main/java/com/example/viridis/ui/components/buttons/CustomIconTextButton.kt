package com.example.viridis.ui.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor

@Composable
fun CustomIconTextButton(
    onClick: () -> Unit,
    text: String,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    containerColor: Color = MainColor,
    contentColor: Color = BackgroundColor,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.width(377.dp).height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor)
    ) {
        when {
            painter != null -> Icon(painter = painter, contentDescription = null)
            imageVector != null -> Icon(imageVector = imageVector, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(text)
    }
}
