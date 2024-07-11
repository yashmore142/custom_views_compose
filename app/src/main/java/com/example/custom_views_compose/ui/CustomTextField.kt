package com.example.custom_views_compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CustomTextFiled(
    label: String = "Demo",
    modifier: Modifier,
    errorMsg: String,
    keyboardOptions: KeyboardOptions,
    drawableRight: ImageVector?,
    validator: (String) -> Boolean,
    onText: (String) -> Unit,
    onValidate: (Boolean) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    var isValid by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier) {

        OutlinedTextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onValueChange = {
                text = it
                onText(it)
                isValid = validator(it)
                onValidate(!isValid)
            },
            leadingIcon = {
                if (drawableRight != null) {
                    Icon(drawableRight, contentDescription = null)
                }
            },
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            },
            keyboardOptions = keyboardOptions
        )
        if (isValid) {
            Text(
                text = errorMsg,
                modifier = Modifier.padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Red,
                    fontFamily = FontFamily.Default
                )
            )
        }
    }

}