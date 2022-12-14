package com.example.socialnetwork.presenter.auth.recover

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.TextFieldCustom
import com.example.socialnetwork.presenter.components.Toolbar
import com.example.socialnetwork.ui.theme.ColorBackgroundApp
import com.example.socialnetwork.ui.theme.ColorPrimaryDark
import com.example.socialnetwork.ui.theme.ColorTextLight
import com.example.socialnetwork.ui.theme.spacing

@ExperimentalMaterial3Api
@Composable
fun RecoverAccountScreen(
    onBackPressed: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Toolbar(title = stringResource(id = R.string.text_title_recover_screen)) { onBackPressed() }

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = stringResource(id = R.string.text_message_recover_screen),
                    color = ColorTextLight,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Text(
                    text = stringResource(id = R.string.text_label_email_recover_screen),
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

                ButtonDefault(
                    text = stringResource(id = R.string.text_btn_recover_account_recover_screen),
                    backgroundColor = ColorPrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            }

        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RecoverAccountScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        RecoverAccountScreen(
            onBackPressed = { }
        )
    }
}