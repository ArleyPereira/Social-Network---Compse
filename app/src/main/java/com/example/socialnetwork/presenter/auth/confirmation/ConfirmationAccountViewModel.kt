package com.example.socialnetwork.presenter.auth.confirmation

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.domain.model.toUserEntity
import com.example.socialnetwork.domain.usecase.api.auth.ConfirmationAccountUseCase
import com.example.socialnetwork.domain.usecase.room.user.InsertUserDbUseCase
import com.example.socialnetwork.presenter.auth.confirmation.event.ConfirmationAccountEvent
import com.example.socialnetwork.presenter.auth.confirmation.event.ConfirmationAccountUIEvent
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ConfirmationAccountViewModel @Inject constructor(
    private val confirmationUseCase: ConfirmationAccountUseCase,
    private val insertUserDbUsecase: InsertUserDbUseCase
) : ViewModel() {

    private val _codeField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_code_confirmation_account_screen
        )
    )
    val codeField: State<TextFieldState> = _codeField

    private val _eventFlow = MutableSharedFlow<ConfirmationAccountUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        timeRunning()
    }

    fun onEvent(event: ConfirmationAccountEvent) {
        when (event) {
            is ConfirmationAccountEvent.EnteredCode -> {
                _codeField.value = codeField.value.copy(text = event.value)
            }
            is ConfirmationAccountEvent.ConfirmationAccount -> {
                confirmationAccount(event.email)
            }
            is ConfirmationAccountEvent.SaveUserDB -> {
                insertUserDB(event.user)
            }
        }
    }

    private fun confirmationAccount(email: String) = viewModelScope.launch {
        try {
            _eventFlow.emit(ConfirmationAccountUIEvent.ConfirmationLoading)

            val body = mapOf(
                "code" to _codeField.value.text,
                "email" to email
            )

            val result = confirmationUseCase.invoke(body)

            if (result.error == true) {
                _eventFlow.emit(
                    ConfirmationAccountUIEvent.ConfirmationError(
                        value = ErrorAPI(
                            error = true,
                            message = result.message
                        )
                    )
                )
            } else {
                _eventFlow.emit(ConfirmationAccountUIEvent.ConfirmationSucess)
            }
        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()
            errorApi?.let {
                _eventFlow.emit(ConfirmationAccountUIEvent.ConfirmationError(it))
            }
        } catch (ex: Exception) {
            _eventFlow.emit(
                ConfirmationAccountUIEvent.ConfirmationError(
                    value = ErrorAPI(
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

            _eventFlow.emit(ConfirmationAccountUIEvent.SaveUserDBSucess)
        } catch (e: Exception) {
            _eventFlow.emit(
                ConfirmationAccountUIEvent.ConfirmationError(
                    ErrorAPI(
                        error = true,
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

    private fun timeRunning() {
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewModelScope.launch {
                    _eventFlow.emit(
                        ConfirmationAccountUIEvent.TimeRunning(
                            time = millisUntilFinished,
                            isTimeRunning = true
                        )
                    )
                }
            }

            override fun onFinish() {
                viewModelScope.launch {
                    _eventFlow.emit(
                        ConfirmationAccountUIEvent.TimeRunning(
                            time = 0,
                            isTimeRunning = false
                        )
                    )
                }
            }
        }.start()
    }

}