package com.example.socialnetwork.presenter.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.usecase.api.auth.LoginUseCase
import com.example.socialnetwork.presenter.auth.login.events.LoginEvent
import com.example.socialnetwork.presenter.auth.login.events.LoginUIEvent
import com.example.socialnetwork.presenter.auth.state.AuthTextFieldState
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUseCase
) : ViewModel() {

    private val _emailField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_label_email_login_screen
        )
    )
    val emailField: State<AuthTextFieldState> = _emailField

    private val _passwordField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_password_login_screen
        )
    )
    val passwordField: State<AuthTextFieldState> = _passwordField

    private val _eventFlow = MutableSharedFlow<LoginUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailField.value = emailField.value.copy(text = event.value)
            }
            is LoginEvent.EnteredPassword -> {
                _passwordField.value = passwordField.value.copy(text = event.value)
            }
            is LoginEvent.Login -> {
                loginApp()
            }
        }
    }

    private fun loginApp() = viewModelScope.launch {
        try {
            _eventFlow.emit(LoginUIEvent.LoginLoading)

            val body = mapOf(
                "email" to emailField.value.text,
                "password" to passwordField.value.text
            )

            val result = loginUsecase.invoke(body)

            result.data?.let { _eventFlow.emit(LoginUIEvent.LoginSucess(result.data)) }

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()

            _eventFlow.emit(
                LoginUIEvent.LoginError(
                    message = errorApi?.message ?: "Ocorreu um erro."
                )
            )
        } catch (ex: Exception) {
            _eventFlow.emit(
                LoginUIEvent.LoginError(
                    message = ex.message ?: "Ocorreu um erro."
                )
            )
        }
    }

}