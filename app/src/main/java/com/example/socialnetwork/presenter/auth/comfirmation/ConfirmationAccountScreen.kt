package com.example.socialnetwork.presenter.auth.comfirmation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.TextFieldCustom
import com.example.socialnetwork.presenter.components.Toolbar
import com.example.socialnetwork.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ConfirmationAccountScreen(
    navigator: DestinationsNavigator,
    email: String,
    viewModel: ConfirmationAccountViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Toolbar(
                title = stringResource(id = R.string.text_title_toolbar_confirmation_account_screen)
            ) {
                navigator.popBackStack()
            }

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = stringResource(id = R.string.text_title_confirmation_account_screen),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = ColorSecondaryDark,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Text(
                    text = stringResource(id = R.string.text_message_confirmation_account_screen),
                    color = ColorTextLight,
                    modifier = Modifier.align(Alignment.Start)
                )

                Text(
                    text = email,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = ColorTextDark,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Text(
                    text = stringResource(id = R.string.text_label_code_confirmation_account_screen),
                    color = ColorTextLight,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                TextFieldCustom(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    hintText = R.string.text_hint_code_confirmation_account_screen,
                    maxLength = 6,
                    onTextChange = {

                    })

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                ButtonDefault(
                    text = stringResource(id = R.string.text_btn_send_code_confirmation_account_screen),
                    textStyle = TextStyle(color = Color.White),
                    backgroundColor = ColorPrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }

                ButtonDefault(
                    text = stringResource(id = R.string.text_btn_resend_code_confirmation_account_screen),
                    textStyle = TextStyle(color = ColorSecondaryDark),
                    backgroundColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ConfirmationAccountScreenPreview() {
    ConfirmationAccountScreen(
        navigator = EmptyDestinationsNavigator,
        email = "email@gmail.com"
    )
}