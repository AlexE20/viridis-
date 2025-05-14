package com.example.viridis.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun CustomTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isSingleLine: Boolean = true,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null, //accepts any type of @Composable, but will be used to Icons
    )
{
    var showPassword by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label, color = Color(0xFF4CAF50))},
        placeholder = { Text(placeholder, color = Color.Gray)},
        modifier = modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)).background(Color(0xFFDFF0CF)),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
            imeAction = ImeAction.Next
        ),
        visualTransformation = when {
            isPassword && !showPassword -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        singleLine = isSingleLine,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.colors( // ✅ Esto es válido a partir de Material 3.2+
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedContainerColor = Color(0xFFDFF0CF),
            unfocusedContainerColor = Color(0xFFDFF0CF)
        ),
        trailingIcon = {
            if (isPassword) {
                    IconButton(onClick = {showPassword = !showPassword}) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Done else Icons.Default.Clear, //Need a library with more icons
                        contentDescription = if (showPassword) "Hide" else "Show"
                    )
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun showTf() {
    CustomTextfield("hola", onValueChange = {}, "TEXT", "BUSO")
}

/*Call example
*
* for password
var password by remember { mutableStateOf("") }

CustomTextField(
    value = password,
    onValueChange = { password = it },
    label = "Password",
    placeholder = "Enter your name",
    isPassword = true,
    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = lock) }
)
*
* //for email
var email by remember { mutableStateOf("") }

CustomTextField(
    value = email,
    onValueChange = { email = it },
    label = "email",
    placeholder = "jotapethegoat66@soy.pro.com",
    keyboardType = KeyboardType.Email,
    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
)
* //for name
var name by remember { mutableStateOf("") }

CustomTextField(
    value = name,
    onValueChange = { name = it },
    label = "Name",
    placeholder = "Jotape",
    keyboardType = KeyboardType.Text,
    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
)
* */
