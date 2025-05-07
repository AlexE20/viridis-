package com.example.viridis.ui.components

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

//A icon only that will have the style based on the figma
@Composable
fun CustomIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    //These are default colors, but can be change when you call the function
    containerColor: Color = Color(0xFFBEE698),
    contentColor: Color = Color(0xFFF9FBE7)
){

    Button(
        onClick = onClick,
        modifier = Modifier.width(80.dp).height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "icon"
        )
    }
}

@Composable
fun CustomIconTextButton(
    onClick: () -> Unit,
    text: String,
    icon: ImageVector,
    containerColor: Color = Color(4278274374),
    contentColor: Color = Color(0xFFF9FBE7),
    modifier: Modifier = Modifier,
){
    Button(
        onClick = onClick,
        modifier = Modifier.width(377.dp).height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor)
    ) {
        Icon(imageVector = icon, contentDescription = "icon")
        Text(text)
    }
    //A button that will have an icon and then the text, like google auth button or delete.
}

@Composable
fun InteractiveText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color(4278274374),
    underline: Boolean = false
){
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(containerColor = Color.Transparent, contentColor = textColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text= text,
            textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
        )
    }
    //A text with onClick action and probably navigation
}

@Preview
@Composable
fun prevCusButton(){
    Column {
        //CustomButton("Hola test", onClick = {}) //viewer and calling example
        //CustomIconButton(Icons.Default.Done, onClick = {})
        //CustomIconTextButton(onClick = {},"Hola test",Icons.Default.Add)
        InteractiveText("hola test", onClick = {})
    }
}