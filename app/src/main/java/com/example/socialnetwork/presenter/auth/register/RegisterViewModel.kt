package com.example.socialnetwork.presenter.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.usecase.api.auth.RegisterUseCase
import com.example.socialnetwork.presenter.auth.register.events.RegisterEvent
import com.example.socialnetwork.presenter.auth.register.events.RegisterUIEvent
import com.example.socialnetwork.presenter.auth.state.AuthTextFieldState
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
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _firstNameField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_first_name_register_fragment
        )
    )
    val firstNameField: State<AuthTextFieldState> = _firstNameField

    private val _lastNameField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_last_name_register_fragment
        )
    )
    val lastNameField: State<AuthTextFieldState> = _lastNameField

    private val _emailField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_email_register_screen
        )
    )
    val emailField: State<AuthTextFieldState> = _emailField

    private val _birthField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_date_birth_register_fragment
        )
    )
    val birthField: State<AuthTextFieldState> = _birthField

    private val _passwordField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_password_register_fragment
        )
    )
    val passwordField: State<AuthTextFieldState> = _passwordField

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

            result.data?.let { _eventFlow.emit(RegisterUIEvent.RegisterSucess(it)) }

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()

            _eventFlow.emit(
                RegisterUIEvent.RegisterError(
                    message = errorApi?.message ?: "Ocorreu um erro."
                )
            )
        } catch (ex: Exception) {
            _eventFlow.emit(
                RegisterUIEvent.RegisterError(
                    message = ex.message ?: "Ocorreu um erro."
                )
            )
        }
    }

    private fun userMapOff(): Map<String, String> {
        return mapOf(
            "first_name" to firstNameField.value.text,
            "last_name" to lastNameField.value.text,
            "date_birth" to birthField.value.text.convertDateBirth(),
            "genre" to "male",
            "email" to emailField.value.text,
            "password" to passwordField.value.text,
            "avatar" to ""
        )
    }

}