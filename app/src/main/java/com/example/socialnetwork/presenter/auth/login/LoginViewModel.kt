package com.example.socialnetwork.presenter.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.data.model.toDomain
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.domain.model.toUserEntity
import com.example.socialnetwork.domain.usecase.api.auth.LoginUseCase
import com.example.socialnetwork.domain.usecase.room.user.InsertUserDbUseCase
import com.example.socialnetwork.presenter.auth.login.events.LoginEvent
import com.example.socialnetwork.presenter.auth.login.events.LoginUIEvent
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.util.getErrorResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUseCase,
    private val insertUserDbUsecase: InsertUserDbUseCase
) : ViewModel() {

    private val _emailField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_label_email_login_screen,
            text = "pequeno_arley@hotmail.com"
            //text = "pequeno_arley@hotmail.com"
        )
    )
    val emailField: State<TextFieldState> = _emailField

    private val _passwordField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_password_login_screen,
            text = "teste123"
            //text = "benedito1965"
        )
    )
    val passwordField: State<TextFieldState> = _passwordField

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

            result.data?.let {
                insertUserDB(it.toDomain())
            }

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()

            errorApi?.let {
                _eventFlow.emit(
                    LoginUIEvent.LoginError(
                        it.copy(
                            data = if (it.data != null) {
                                Gson().fromJson(
                                    Gson().toJson(errorApi.data),
                                    UserDto::class.java
                                ).toDomain()
                            } else {
                                null
                            }
                        )
                    )
                )
            }

        } catch (ex: Exception) {
            _eventFlow.emit(
                LoginUIEvent.LoginError(
                    value = ErrorAPI(
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

    private fun insertUserDB(user: User) = viewModelScope.launch {
        try {
            insertUserDbUsecase.invoke(user.toUserEntity())

            _eventFlow.emit(LoginUIEvent.LoginSucess(user))
        } catch (e: Exception) {
            _eventFlow.emit(
                LoginUIEvent.LoginError(
                    ErrorAPI(
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

}