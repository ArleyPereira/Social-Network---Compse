package com.example.socialnetwork.presenter.auth.login

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.presenter.auth.login.events.LoginEvent
import com.example.socialnetwork.presenter.auth.login.events.LoginUIEvent
import com.example.socialnetwork.presenter.components.*
import com.example.socialnetwork.presenter.destinations.RecoverAccountScreenDestination
import com.example.socialnetwork.presenter.destinations.RegisterScreenDestination
import com.example.socialnetwork.ui.theme.*
import com.example.socialnetwork.util.Constants.Actions.ACTION_INVALID_DATA
import com.example.socialnetwork.util.Constants.Actions.ACTION_NOT_CONFIRMED
import com.example.socialnetwork.util.Constants.Actions.ACTION_NOT_FOUND
import com.example.socialnetwork.util.Constants.Actions.ACTION_REQUIRED_FIELDS
import com.example.socialnetwork.util.Constants.Actions.ACTION_UNSUCCESSFULLY
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var apiError by remember { mutableStateOf(LoginUIEvent.LoginError(ErrorAPI())) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    var isLoading by remember { mutableStateOf(false) }

    val emailField = viewModel.emailField.value
    val passwordField = viewModel.passwordField.value

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

                    apiError = LoginUIEvent.LoginError(value = event.value)

                    coroutineScope.launch {
                        sheetState.animateTo(
                            ModalBottomSheetValue.Expanded,
                            tween(500)
                        )
                    }
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetScreen(
                message = apiError.value.message,
                onClickOk = {
                    coroutineScope.launch {
                        sheetState.hide()

                        if (apiError.value.error) {
                            when (apiError.value.action) {
                                ACTION_NOT_CONFIRMED -> {

                                }
                            }
                        }
                    }
                },
                onClickCancel = {
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
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
                    hintText = emailField.hint,
                    text = emailField.text,
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
                    hintText = passwordField.hint,
                    text = passwordField.text,
                    onTextChange = {
                        viewModel.onEvent(LoginEvent.EnteredPassword(it))
                    })

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                ButtonDefault(
                    text = stringResource(id = R.string.text_btn_login_login_screen),
                    textStyle = TextStyle(color = Color.White),
                    backgroundColor = ColorPrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    coroutineScope.launch {
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
                        color = ColorTextLight
                    )

                    if (isLoading) {
                        CircularProgressIndicator()
                    }

                    Text(
                        text = stringResource(id = R.string.text_recover_login_screen),
                        modifier = Modifier
                            .clickable { navigator.navigate(RecoverAccountScreenDestination) },
                        color = ColorTextLight
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
                            .background(color = Color.White)
                            .padding(horizontal = MaterialTheme.spacing.normal),
                        color = ColorTextLight
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
@ExperimentalMaterialApi
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