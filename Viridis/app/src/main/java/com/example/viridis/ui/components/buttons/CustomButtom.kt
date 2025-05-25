package com.example.viridis.ui.components.buttons

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
import com.example.viridis.ui.theme.BackgroundColor
import com.example.viridis.ui.theme.MainColor

@Composable
fun CustomButton(
    text:String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

){
    Button(
        onClick = onClick,
        modifier = Modifier.width(377.dp).height(52.dp),
        colors = ButtonDefaults.buttonColors(MainColor)
    ) {
        Text(text, color = BackgroundColor, fontSize = 20.sp)
    }
}


@Preview
@Composable
fun prevCusButton(){
    Column {
        CustomButton("Hola test", onClick = {}) //viewer and calling example
    }
}