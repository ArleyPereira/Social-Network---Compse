package com.example.socialnetwork.presenter.auth.comfirmation.event

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.User

sealed class ConfirmationAccountUIEvent {

    data class TimeRunning(val time: Long, val isTimeRunning: Boolean) :
        ConfirmationAccountUIEvent()

    data class ConfirmationSucess(val user: User) : ConfirmationAccountUIEvent()
    data class ConfirmationError(val value: ErrorAPI) : ConfirmationAccountUIEvent()

    object ConfirmationLoading : ConfirmationAccountUIEvent()
}