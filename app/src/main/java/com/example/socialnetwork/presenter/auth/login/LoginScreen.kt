package com.example.socialnetwork.presenter.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.ButtonSocialLogin
import com.example.socialnetwork.presenter.components.TextFieldCustom
import com.example.socialnetwork.presenter.components.TextFieldPassword
import com.example.socialnetwork.ui.theme.*

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit,
    onLoginInApp: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Text(
                text = stringResource(id = R.string.text_title_login_screen),
                color = ColorSecondaryDark,
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Text(
                text = stringResource(id = R.string.text_label_email_login_screen),
                color = ColorTextLight,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextFieldCustom(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                hintText = "email@gmail.com",
                onTextChange = {

                })

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            Text(
                text = stringResource(id = R.string.text_label_password_login_screen),
                color = ColorTextLight,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextFieldPassword(hintText = "******", onTextChange = {

            })

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            ButtonDefault(
                text = stringResource(id = R.string.text_btn_login_login_screen),
                backgroundColor = ColorPrimaryDark,
                modifier = Modifier
                    .fillMaxWidth()
            ) { onLoginInApp() }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.text_register_login_screen),
                    modifier = Modifier
                        .clickable { onRegisterClick() },
                    color = Color(0xFF969CAE)
                )

                Text(
                    text = stringResource(id = R.string.text_recover_login_screen),
                    modifier = Modifier
                        .clickable { onRecoverClick() },
                    color = Color(0xFF969CAE)
                )

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Center
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
                    text = stringResource(id = R.string.text_continue_with_login_screen),
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

@Preview
@Composable
fun LoginScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        LoginScreen(
            onRegisterClick = {},
            onRecoverClick = {},
            onLoginInApp = {}
        )
    }
}