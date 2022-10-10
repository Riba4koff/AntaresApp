package com.example.antaresapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: String ?= null
) {
    val focus = LocalFocusManager.current
    when (value) {
        "telegram" -> TextField(
            modifier = modifier,
            value = "@" + value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = {
                focus.clearFocus()
            }),
            singleLine = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        else -> TextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focus.clearFocus()
            }),
            singleLine = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                placeholder?.let { Row(modifier = Modifier.fillMaxWidth()){Text(text = it, fontSize = 16.sp)} }
            }
        )
    }
}

@Composable
fun MyText(
    value: String
) {
    Text(
        text = value,
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp),
        fontSize = 13.sp,
        letterSpacing = 1.3.sp,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Gray,
                offset = Offset(x = 0f, y = 1f),
                blurRadius = 0.5f
            )
        )
    )
}


@Composable
fun MyRow(placeholder: String, onValueChange: (String) -> Unit = {}, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, myColor))
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
    ) {
        MyTextField(
            modifier = Modifier.width(320.dp),
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }
}
