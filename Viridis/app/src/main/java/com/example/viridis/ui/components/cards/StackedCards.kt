package com.example.viridis.ui.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.SecondaryAccent
import com.example.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.Image
import com.example.viridis.ui.theme.urbanistFont

@ExperimentalMaterial3Api
@Composable
fun StakedCards(
    modifier: Modifier = Modifier,
    gardenName: String,
    gardenShade: String,
    imageUrls: List<String>? = null,
    clickable: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(SecondaryAccent),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = clickable
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (imageUrls.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .height(180.dp).fillMaxSize().background(BackgroundColor, RoundedCornerShape(8.dp)).border(1.dp, SecondaryAccent, RoundedCornerShape(8.dp))
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val w = size.width
                        val h = size.height
                        val halfW = w / 2
                        val halfH = h / 2
                        val paint = Paint().apply { color = Color(0xFFBAC6B1) }
                        drawLine(paint.color, Offset(halfW, 0f), Offset(halfW, h), strokeWidth = 2.dp.toPx())
                        drawLine(paint.color, Offset(0f, halfH), Offset(w, halfH), strokeWidth = 2.dp.toPx())
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxSize()
                ) {
                    imageUrls.forEachIndexed { index, url ->
                        val offset = (index * 16).dp
                        Image(
                            painter = rememberAsyncImagePainter(url),
                            contentDescription = "Imagen de planta",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .matchParentSize()
                                .padding(start = offset, top = offset)
                                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = gardenName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = urbanistFont,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = BackgroundColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = gardenShade,
                color = BackgroundColor,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = urbanistFont ,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



