package com.example.socialnetwork.presenter.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.toDomain
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.domain.model.toUserEntity
import com.example.socialnetwork.domain.usecase.api.auth.RegisterUseCase
import com.example.socialnetwork.domain.usecase.room.user.InsertUserDbUseCase
import com.example.socialnetwork.presenter.auth.register.events.RegisterEvent
import com.example.socialnetwork.presenter.auth.register.events.RegisterUIEvent
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.util.convertDateBirth
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val insertUserDbUsecase: InsertUserDbUseCase
) : ViewModel() {

    private val _firstNameField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_first_name_register_fragment
        )
    )
    val firstNameField: State<TextFieldState> = _firstNameField

    private val _lastNameField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_last_name_register_fragment
        )
    )
    val lastNameField: State<TextFieldState> = _lastNameField

    private val _emailField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_email_register_screen
        )
    )
    val emailField: State<TextFieldState> = _emailField

    private val _birthField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_date_birth_register_fragment
        )
    )
    val birthField: State<TextFieldState> = _birthField

    private val _passwordField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_password_register_fragment
        )
    )
    val passwordField: State<TextFieldState> = _passwordField

    private val _eventFlow = MutableSharedFlow<RegisterUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredFirstName -> {
                _firstNameField.value = firstNameField.value.copy(text = event.value)
            }
            is RegisterEvent.EnteredLastName -> {
                _lastNameField.value = lastNameField.value.copy(text = event.value)
            }
            is RegisterEvent.EnteredEmail -> {
                _emailField.value = emailField.value.copy(text = event.value)
            }
            is RegisterEvent.EnteredBirth -> {
                _birthField.value = birthField.value.copy(text = event.value)
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordField.value = passwordField.value.copy(text = event.value)
            }
            is RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun register() = viewModelScope.launch {
        try {
            _eventFlow.emit(RegisterUIEvent.RegisterLoading)

            val result = registerUseCase.invoke(userMapOff())

            result.data?.let { insertUserDB(it.toDomain()) }
        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()

            errorApi?.let { _eventFlow.emit(RegisterUIEvent.RegisterError(it)) }
        } catch (ex: Exception) {
            _eventFlow.emit(
                RegisterUIEvent.RegisterError(
                    ErrorAPI(
                        error = true,
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

    private fun insertUserDB(user: User) = viewModelScope.launch {
        try {
            insertUserDbUsecase.invoke(user.toUserEntity())

            _eventFlow.emit(RegisterUIEvent.RegisterSucess(user))
        } catch (e: Exception) {
            _eventFlow.emit(
                RegisterUIEvent.RegisterError(
                    ErrorAPI(
                        error = true,
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

    private fun userMapOff(): Map<String, String> {
        return mapOf(
            "first_name" to "Arley",//firstNameField.value.text,
            "last_name" to "Santana",//lastNameField.value.text,
            "date_birth" to "28-04-1995".convertDateBirth(),
            "genre" to "male",
            "email" to "pequeno_arley@hotmail.com",//emailField.value.text,
            "password" to "teste123",//passwordField.value.text,
            "avatar" to ""
        )
    }

}