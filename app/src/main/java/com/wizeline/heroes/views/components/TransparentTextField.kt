package com.wizeline.heroes.views.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.ui.graphics.Color


@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    textFieldValue: MutableState<String>,
    textLabel:String,
    maxChar: Int? = null,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None, //Cada palabra que escriba la primera sera en mayuscula, com oesta ne none se desactiva
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction,
    trailingIcon: @Composable() (()-> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
//visualTransformation: PasswordVisualTransformation
){
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = textFieldValue.value.take(maxChar ?: 40),
        onValueChange = { textFieldValue.value = it },
        label = {
            Text(text = textLabel)
        },
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}
