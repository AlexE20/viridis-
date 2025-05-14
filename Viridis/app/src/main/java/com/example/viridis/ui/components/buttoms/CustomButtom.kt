package com.example.viridis.ui.components.buttoms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text:String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, //optional modifier in case to change the height or width

){
    //default based figma Button style, can change the width and height
    Button(
        onClick = onClick,
        modifier = Modifier.width(377.dp).height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(4278274374))
    ) {
        Text(text)
    }
}


@Preview
@Composable
fun prevCusButton(){
    Column {
        CustomButton("Hola test", onClick = {}) //viewer and calling example
    }
}