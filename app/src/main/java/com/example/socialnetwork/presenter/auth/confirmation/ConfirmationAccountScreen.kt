package com.example.socialnetwork.presenter.auth.confirmation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
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
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.presenter.auth.confirmation.event.ConfirmationAccountEvent
import com.example.socialnetwork.presenter.auth.confirmation.event.ConfirmationAccountUIEvent
import com.example.socialnetwork.presenter.components.*
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.example.socialnetwork.presenter.destinations.LoginScreenDestination
import com.example.socialnetwork.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Destination
@Composable
fun ConfirmationAccountScreen(
    navigator: DestinationsNavigator,
    user: User,
    viewModel: ConfirmationAccountViewModel = hiltViewModel()
) {

    var apiError by remember {
        mutableStateOf(
            ConfirmationAccountUIEvent.ConfirmationError(
                ErrorAPI(
                    null
                )
            )
        )
    }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    var isLoading by remember { mutableStateOf(false) }
    var isTimeRunning by remember { mutableStateOf(0L) }

    val codeField = viewModel.codeField.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ConfirmationAccountUIEvent.TimeRunning -> {
                    isTimeRunning = event.time
                }
                is ConfirmationAccountUIEvent.ConfirmationError -> {
                    isLoading = false

                    apiError = ConfirmationAccountUIEvent.ConfirmationError(event.value)

                    coroutineScope.launch {
                        sheetState.animateTo(
                            ModalBottomSheetValue.Expanded,
                            tween(500)
                        )
                    }
                }
                is ConfirmationAccountUIEvent.ConfirmationLoading -> {
                    isLoading = true
                }
                is ConfirmationAccountUIEvent.ConfirmationSucess -> {
                    viewModel.onEvent(ConfirmationAccountEvent.SaveUserDB(user))
                }
                is ConfirmationAccountUIEvent.SaveUserDBSucess -> {
                    navigator.navigate(FeedScreenDestination) {
                        popUpTo(LoginScreenDestination.route) { inclusive = true }
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
                        text = user.email ?: "",
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
                            keyboardType = KeyboardType.Text
                        ),
                        hintText = codeField.hint,
                        text = codeField.text,
                        onTextChange = {
                            viewModel.onEvent(ConfirmationAccountEvent.EnteredCode(it))
                        })

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

                    ButtonDefault(
                        text = stringResource(id = R.string.text_btn_send_code_confirmation_account_screen),
                        textStyle = TextStyle(color = Color.White),
                        backgroundColor = ColorPrimaryDark,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        viewModel.onEvent(ConfirmationAccountEvent.ConfirmationAccount(user.email))
                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                    if (isLoading) {
                        CircularProgressIndicator()
                    }

                }

            }

            ButtonResend(
                isTimeRunning = isTimeRunning,
                textStyle = TextStyle(color = ColorSecondaryDark),
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.normal
                    )
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {

            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun ConfirmationAccountScreenPreview() {
    ConfirmationAccountScreen(
        navigator = EmptyDestinationsNavigator,
        user = User(
            email = "debug@gmail.com"
        )
    )
}