package com.example.viridis.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun CustomIconButton(){
    //Work in progress
    //A text with and icon only that will have the style based on the figma
}

@Composable
fun CustomIconTextButton(){
    //Work in progress
    //A button that will have an icon and then the text, like google auth button or delete.
}

@Composable
fun InteractiveText(){
    //Work in progress
    //A text with onClick action and probably navigation
}

@Preview
@Composable
fun prevCusButton(){
    CustomButton("Hola test", onClick = {}) //viewer and calling example
}