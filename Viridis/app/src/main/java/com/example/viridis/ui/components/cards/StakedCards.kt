package com.example.viridis.ui.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.SecondaryAccent

@ExperimentalMaterial3Api
@Composable
fun StakedCards(
    modifier: Modifier = Modifier,
    gardenName: String,
    gardenDescription: String,
    imageUrls: List<String>? = null // list for images
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(SecondaryAccent),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (imageUrls.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .height(120.dp).fillMaxSize().background(BackgroundColor, RoundedCornerShape(8.dp)).border(1.dp, SecondaryAccent, RoundedCornerShape(8.dp))
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
                //to show the images
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = gardenName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = BackgroundColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = gardenDescription,
                color = BackgroundColor,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GardenCardPreview() {
        StakedCards(
            gardenName = "Daniel's Studio",
            gardenDescription = "Full shade",
            imageUrls = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
}


