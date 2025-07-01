package com.pdm.viridis.ui.components.cards

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
import com.pdm.viridis.ui.theme.BackgroundColor
import com.pdm.viridis.ui.theme.SecondaryAccent
import com.pdm.viridis.ui.theme.urbanistFont
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip

@ExperimentalMaterial3Api
@Composable
fun StakedCards(
    modifier: Modifier = Modifier,
    gardenName: String,
    gardenShade: String,
    imageUrls: List<String>? = null,
    clickable: () -> Unit,
    isFavorite : Boolean = true
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
                        .height(180.dp)
                        .fillMaxSize().background(BackgroundColor, RoundedCornerShape(8.dp)).border(1.dp, SecondaryAccent, RoundedCornerShape(8.dp))
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
                BoxWithConstraints(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .background(BackgroundColor, RoundedCornerShape(8.dp))
                        .border(1.dp, SecondaryAccent, RoundedCornerShape(8.dp))
                ) {
                    with(this) {

                        val cellWidth = maxWidth / 2
                        val cellHeight = maxHeight / 2

                        Canvas(modifier = Modifier.matchParentSize()) {
                            val w = size.width
                            val h = size.height
                            drawLine(BackgroundColor, Offset(w / 2, 0f), Offset(w / 2, h), 4.dp.toPx())
                            drawLine(BackgroundColor, Offset(0f, h / 2), Offset(w, h / 2), 4.dp.toPx())
                        }
                        imageUrls.orEmpty().take(4).forEachIndexed { index, url ->
                            val row = index / 2
                            val col = index % 2

                            Image(
                                painter = rememberAsyncImagePainter(url),
                                contentDescription = "Imagen de planta",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .offset(
                                        x = cellWidth * col,
                                        y = cellHeight * row
                                    )
                                    .size(cellWidth, cellHeight)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = gardenName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = urbanistFont,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = BackgroundColor
                )
                Spacer(Modifier.width(4.dp))
                if (isFavorite){
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "fav",
                        tint = BackgroundColor,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }

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



