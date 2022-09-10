package com.example.socialnetwork.presenter.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.components.*
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryDark
import com.example.socialnetwork.ui.theme.ColorTextLight
import com.example.socialnetwork.ui.theme.spacing

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Toolbar(title = stringResource(id = R.string.text_title_register_screen)) { onBackPressed() }

            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_label_first_name_register_fragment),
                            color = ColorTextLight,
                            modifier = Modifier
                                .padding(bottom = MaterialTheme.spacing.extraSmall)
                                .align(Alignment.Start)
                        )

                        TextFieldCustom(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            ),
                            hintText = stringResource(id = R.string.text_label_first_name_register_fragment),
                            onTextChange = {

                            })
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))

                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_label_last_name_register_fragment),
                            color = ColorTextLight,
                            modifier = Modifier
                                .padding(bottom = MaterialTheme.spacing.extraSmall)
                                .align(Alignment.Start)
                        )

                        TextFieldCustom(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            ),
                            hintText = stringResource(id = R.string.text_label_last_name_register_fragment),
                            onTextChange = {

                            })
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                Text(
                    text = stringResource(id = R.string.text_label_email_register_fragment),
                    color = ColorTextLight,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall)
                        .align(Alignment.Start)
                )

                TextFieldCustom(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    hintText = stringResource(id = R.string.text_hint_email_register_fragment),
                    onTextChange = {

                    })

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                Text(
                    text = stringResource(id = R.string.text_label_date_birth_register_fragment),
                    color = ColorTextLight,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall)
                        .align(Alignment.Start)
                )

                TextFieldCustom(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    hintText = stringResource(id = R.string.text_hint_date_birth_register_fragment),
                    onTextChange = {

                    })

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                Text(
                    text = stringResource(id = R.string.text_label_password_register_fragment),
                    color = ColorTextLight,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                        .align(Alignment.Start)
                )

                TextFieldPassword(hintText = stringResource(id = R.string.text_hint_password_register_fragment), onTextChange = {

                })

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                ButtonDefault(
                    text = stringResource(id = R.string.text_btn_register_registration_initial_fragment),
                    backgroundColor = ColorPrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .alpha(0.1f)
                            .background(Color(0xFF374467))

                    ) {

                    }

                    Text(
                        text = stringResource(id = R.string.text_continue_with_register_screen),
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.bg_app))
                            .padding(horizontal = MaterialTheme.spacing.normal),
                        color = Color(0xFF969CAE)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.normal)
                ) {

                    ButtonSocialLogin(iconId = R.drawable.ic_google) {

                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))

                    ButtonSocialLogin(iconId = R.drawable.ic_facebook) {

                    }

                }

            }
        }

    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RegisterScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        RegisterScreen(
            onBackPressed = { }
        )
    }
}