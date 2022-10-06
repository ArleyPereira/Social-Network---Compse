package com.example.socialnetwork.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
import com.example.socialnetwork.ui.theme.ColorTextHint

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    hintText: Int,
    maxLines: Int = 1,
    text: String = "",
    maxLength: Int = 0,
    onTextChange: (String) -> Unit
) {

    var textValue by remember { mutableStateOf(text) }

    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = if (maxLength > 0) {
                if (it.length <= maxLength) {
                    it
                } else {
                    textValue
                }
            } else {
                it
            }
            onTextChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .border(1.dp, ColorPrimaryLight, RoundedCornerShape(percent = 10))
                    .background(Color.White, RoundedCornerShape(percent = 10))
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                if (textValue.isEmpty()) {
                    Text(
                        text = stringResource(id = hintText),
                        color = ColorTextHint
                    )
                }

                innerTextField()
            }
        }
    )

}

@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
    ),
    hintText: Int,
    maxLines: Int = 1,
    text: String = "",
    onTextChange: (String) -> Unit
) {

    var textValue by remember { mutableStateOf(text) }
    var showPassword by remember { mutableStateOf(false) }
    var showIconPassword by remember { mutableStateOf(false) }

    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = it.trim()
            onTextChange(it.trim())
            showIconPassword = it.trim().isNotEmpty()
        },
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        maxLines = maxLines,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .border(1.dp, ColorPrimaryLight, RoundedCornerShape(percent = 10))
                    .background(Color.White, RoundedCornerShape(percent = 10))
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                if (textValue.isEmpty()) {
                    Text(
                        text = stringResource(id = hintText),
                        modifier = modifier
                            .align(Alignment.CenterStart),
                        color = ColorTextHint
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(end = 36.dp)
                        .fillMaxWidth()
                ) {
                    innerTextField()
                }

                if (showIconPassword) {
                    Icon(
                        painter = painterResource(id = if (showPassword) R.drawable.ic_hide_password else R.drawable.ic_show_password),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { showPassword = !showPassword }
                            .align(Alignment.CenterEnd)
                            .size(24.dp),
                        tint = ColorPrimaryLight
                    )
                }

            }
        }
    )

}

@Preview
@Composable
fun PreviewMessageCard() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .padding(16.dp)
            .fillMaxSize()
    ) {

        TextFieldPassword(hintText = R.string.text_hint_password_login_screen, onTextChange = {

        })

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldCustom(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            hintText = R.string.text_hint_email_login_screen,
            onTextChange = {})

    }
}