package com.pdm.viridis.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.urbanistFont

@Composable
fun BottomAlertCard(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    message: String,
    buttonText: String? = null,
    onDismiss: () -> Unit,
    onButtonClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = onButtonClick == null) { onDismiss() }
            .background(Color.Black.copy(alpha = 0.3f))
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7E8)) // como en la imagen
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                icon()
                Spacer(modifier = Modifier.height(height = 16.dp))
                Text(
                    text = message,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = urbanistFont,
                    textAlign = TextAlign.Center
                )
                buttonText?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onButtonClick?.invoke()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF945B00))
                    ) {
                        Text(it)
                    }
                }
            }
        }
    }
}

