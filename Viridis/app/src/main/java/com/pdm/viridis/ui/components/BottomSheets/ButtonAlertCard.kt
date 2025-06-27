package com.pdm.viridis.ui.components.BottomSheets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.WarningColor
import com.pdm.viridis.ui.theme.urbanistFont


@Composable
fun BottomAlertCard(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    message: String,
    buttonText: String? = null,
    onDismiss: () -> Unit,
    onButtonClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = onButtonClick == null) { onDismiss() }
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = message,
                    color = WarningColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = urbanistFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                buttonText?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onButtonClick?.invoke()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = WarningColor),
                        modifier = Modifier.fillMaxWidth(0.8f) // Give button a max width
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomAlertCardPreview() {
    BottomAlertCard(
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning",
                tint = WarningColor
            )
        },
        message = "This is an important message that should be centered properly in the card",
        buttonText = "OK",
        onDismiss = {},
        onButtonClick = {}
    )
}

/*@Composable
fun BottomAlertCard(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    message: String,
    buttonText: String? = null,
    onDismiss: () -> Unit,
    onButtonClick: (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null, //accepts any type of @Composable, but will be used to Icons
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = onButtonClick == null) { onDismiss() }
            .background(Color.Black.copy(alpha = 0.5f))
            ,
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(height = 16.dp))
                Text(
                    text = message,
                    color = WarningColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = urbanistFont,
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {

                }

                buttonText?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onButtonClick?.invoke()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = WarningColor)
                    ) {
                        Text(it)
                    }
                }
            }
        }
    }
}*/

