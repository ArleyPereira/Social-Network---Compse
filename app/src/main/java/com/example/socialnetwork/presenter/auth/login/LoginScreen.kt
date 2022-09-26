package com.example.socialnetwork.presenter.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.components.ButtonDefault
import com.example.socialnetwork.presenter.components.ButtonSocialLogin
import com.example.socialnetwork.presenter.components.TextFieldCustom
import com.example.socialnetwork.presenter.components.TextFieldPassword
import com.example.socialnetwork.presenter.destinations.RecoverAccountScreenDestination
import com.example.socialnetwork.presenter.destinations.RegisterScreenDestination
import com.example.socialnetwork.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var isLoading by remember { mutableStateOf(false) }

    val emailState = viewModel.emailField.value
    val passwordState = viewModel.passwordField.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginUIEvent.LoginLoading -> {
                    isLoading = true
                }
                is LoginUIEvent.LoginSucess -> {

                }
                is LoginUIEvent.LoginError -> {
                    isLoading = false
                }
            }
        }
    }

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
                hintText = emailState.hint,
                text = emailState.text,
                onTextChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                })

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            Text(
                text = stringResource(id = R.string.text_label_password_login_screen),
                color = ColorTextLight,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextFieldPassword(
                hintText = passwordState.hint,
                text = passwordState.text,
                onTextChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                })

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

            ButtonDefault(
                text = stringResource(id = R.string.text_btn_login_login_screen),
                backgroundColor = ColorPrimaryDark,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                scope.launch {
                    viewModel.onEvent(LoginEvent.Login)
                }
//                navigator.navigate(FeedScreenDestination) {
//                    popUpTo(LoginScreenDestination.route) { inclusive = true }
//                }
            }

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
                        .clickable { navigator.navigate(RegisterScreenDestination) },
                    color = Color(0xFF969CAE)
                )

                if (isLoading) {
                    CircularProgressIndicator()
                }

                Text(
                    text = stringResource(id = R.string.text_recover_login_screen),
                    modifier = Modifier
                        .clickable { navigator.navigate(RecoverAccountScreenDestination) },
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

@ExperimentalMaterial3Api
@Preview
@Composable
fun LoginScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        LoginScreen(EmptyDestinationsNavigator)
    }
}