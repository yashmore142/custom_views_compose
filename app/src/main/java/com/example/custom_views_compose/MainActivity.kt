package com.example.custom_views_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.custom_views_compose.ui.CustomTextFiled
import com.example.custom_views_compose.ui.theme.Custom_views_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Custom_views_composeTheme {
                // A surface container using the 'background' color from the theme
                Greeting()

            }
        }
    }
}

@Preview
@Composable
fun Greeting() {

    var email = ""
    var name = ""
    var phone = ""
    val emailValidator: (String) -> Boolean = { email ->
        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    val phoneValidator: (String) -> Boolean = { phone ->
              phone.isEmpty() || phone.length >= 10 && phone.all { it.isDigit() }

    }
    val nameValidator: (String) -> Boolean = { name1 ->
        name1.isEmpty()
    }
    var emailIsValidate by remember {
        mutableStateOf(false)
    }
    var nameIsValidate by remember {
        mutableStateOf(false)
    }
    var phoneIsValidate by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        CustomTextFiled(
            label = stringResource(R.string.enter_email),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            errorMsg = stringResource(R.string.enter_valid_email_address),
            drawableRight = Icons.TwoTone.Email,
            onText = {
                email = it
            },
            validator = emailValidator,
            onValidate = {
                emailIsValidate = it
                Log.i("valid", "email $emailIsValidate")
            }
        )
        CustomTextFiled(
            label = stringResource(R.string.enter_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            errorMsg = stringResource(R.string.enter_valid_name),
            drawableRight = Icons.TwoTone.AccountCircle,
            onText = {
                name = it
            },
            validator = nameValidator,
            onValidate = {
                nameIsValidate = it
                Log.i("valid", "name $nameIsValidate")

            }
        )
        CustomTextFiled(
            label = stringResource(R.string.enter_phone_no),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            errorMsg = stringResource(R.string.enter_valid_phone_no),
            drawableRight = Icons.TwoTone.Phone,
            onText = {
                name = it
            },
            validator = phoneValidator,
            onValidate = {
                phoneIsValidate = it
                Log.i("valid", "phone $phoneIsValidate")

            }
        )
        Button(

            onClick = {
                Log.i("valid", "$phoneIsValidate  $emailIsValidate  $nameIsValidate")
            }, enabled = phoneIsValidate && emailIsValidate && nameIsValidate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp, vertical = 10.dp
                )
        ) {
            Text(stringResource(R.string.validate))
        }

    }
}

