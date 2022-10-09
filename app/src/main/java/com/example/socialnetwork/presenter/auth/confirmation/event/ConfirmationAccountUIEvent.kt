package com.example.socialnetwork.presenter.auth.confirmation.event

import com.example.socialnetwork.data.model.ErrorAPI

sealed class ConfirmationAccountUIEvent {

    data class TimeRunning(val time: Long, val isTimeRunning: Boolean) :
        ConfirmationAccountUIEvent()

    data class ConfirmationError(val value: ErrorAPI) : ConfirmationAccountUIEvent()
    object SaveUserDBSucess : ConfirmationAccountUIEvent()

    object ConfirmationSucess : ConfirmationAccountUIEvent()
    object ConfirmationLoading : ConfirmationAccountUIEvent()
}