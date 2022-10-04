package com.example.socialnetwork.presenter.auth.comfirmation

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.db.entity.toUserEntity
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.domain.usecase.api.auth.RecoverUseCase
import com.example.socialnetwork.domain.usecase.room.user.InsertUserDbUsecase
import com.example.socialnetwork.presenter.auth.comfirmation.event.ConfirmationAccountEvent
import com.example.socialnetwork.presenter.auth.comfirmation.event.ConfirmationAccountUIEvent
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
class ConfirmationAccountViewModel @Inject constructor(
    private val recoverUseCase: RecoverUseCase,
    private val insertUserDbUsecase: InsertUserDbUsecase
) : ViewModel() {

    private val _codeField = mutableStateOf(
        AuthTextFieldState(
            hint = R.string.text_hint_code_confirmation_account_screen
        )
    )
    val codeField: State<AuthTextFieldState> = _codeField

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
            else -> {

            }
        }
    }

    private fun recoverAccount() = viewModelScope.launch {
        try {
            _eventFlow.emit(ConfirmationAccountUIEvent.ConfirmationLoading)

            val body = mapOf(
                "email" to _codeField.value.text
            )

            val result = recoverUseCase.invoke(body)

            result.data?.let {
                //insertUserDB(it)
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

            _eventFlow.emit(ConfirmationAccountUIEvent.ConfirmationSucess(user))
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