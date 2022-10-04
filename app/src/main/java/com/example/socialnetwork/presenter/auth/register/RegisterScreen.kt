package com.example.socialnetwork.presenter.auth.register

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.auth.register.events.RegisterEvent
import com.example.socialnetwork.presenter.auth.register.events.RegisterUIEvent
import com.example.socialnetwork.presenter.components.*
import com.example.socialnetwork.presenter.destinations.ConfirmationAccountScreenDestination
import com.example.socialnetwork.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var error by remember { mutableStateOf(RegisterUIEvent.RegisterError("")) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    var isLoading by remember { mutableStateOf(false) }

    val firstNameField = viewModel.firstNameField.value
    val lastNameField = viewModel.lastNameField.value
    val emailField = viewModel.emailField.value
    val birthState = viewModel.birthField.value
    val passwordField = viewModel.passwordField.value


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RegisterUIEvent.RegisterLoading -> {
                    isLoading = true
                }
                is RegisterUIEvent.RegisterSucess -> {
                    navigator.navigate(ConfirmationAccountScreenDestination(event.user.email ?: ""))
                }
                is RegisterUIEvent.RegisterError -> {
                    isLoading = false

                    error = RegisterUIEvent.RegisterError(message = event.message)

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
                message = error.message,
                onClickOk = {
                    coroutineScope.launch { sheetState.hide() }
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
                    .fillMaxSize()
            ) {
                Toolbar(title = stringResource(id = R.string.text_title_register_screen)) { navigator.popBackStack() }

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
                                hintText = firstNameField.hint,
                                onTextChange = {
                                    viewModel.onEvent(RegisterEvent.EnteredFirstName(it))
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
                                hintText = lastNameField.hint,
                                onTextChange = {
                                    viewModel.onEvent(RegisterEvent.EnteredLastName(it))
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
                        hintText = emailField.hint,
                        onTextChange = {
                            viewModel.onEvent(RegisterEvent.EnteredEmail(it))
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
                        hintText = birthState.hint,
                        onTextChange = {
                            viewModel.onEvent(RegisterEvent.EnteredBirth(it))
                        })

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                    Text(
                        text = stringResource(id = R.string.text_label_password_register_fragment),
                        color = ColorTextLight,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .align(Alignment.Start)
                    )

                    TextFieldPassword(
                        hintText = passwordField.hint,
                        onTextChange = {
                            viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                        })

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                    ButtonDefault(
                        text = stringResource(id = R.string.text_btn_register_registration_initial_fragment),
                        textStyle = TextStyle(color = Color.White),
                        backgroundColor = ColorPrimaryDark,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        coroutineScope.launch {
                            viewModel.onEvent(RegisterEvent.Register)
                        }
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    if (isLoading) {
                        CircularProgressIndicator()
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
                                .background(color = Color.White)
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
}

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Preview
@Composable
fun RegisterScreenPreview() {
    Column(
        modifier = Modifier
            .background(ColorBackgroundApp)
            .fillMaxSize()
    ) {
        RegisterScreen(EmptyDestinationsNavigator)
    }
}