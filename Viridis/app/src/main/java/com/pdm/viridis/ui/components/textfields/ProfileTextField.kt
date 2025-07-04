package com.pdm.viridis.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.viridis.ui.theme.MainAccent
import com.pdm.viridis.ui.theme.SecondaryAccent

@Composable
fun ProfileTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isSingleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null, //accepts any type of @Composable, but will be used to Icons
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                color = SecondaryAccent,
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                leadingIcon?.invoke()
            }
        },
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MainAccent,
            unfocusedContainerColor = MainAccent,
            disabledContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = SecondaryAccent,
            unfocusedTextColor = SecondaryAccent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileTextfieldPreview() {
    var gardenName by remember { mutableStateOf("") }

    ProfileTextfield(
        value = gardenName,
        onValueChange = { gardenName = it },
        placeholder = "Name your garden",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit icon",
                tint = SecondaryAccent,
                modifier = Modifier.size(20.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(SecondaryAccent)
    )
}

