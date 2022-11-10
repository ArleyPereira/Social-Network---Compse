package com.example.socialnetwork.presenter.auth.confirmation.event

import com.example.socialnetwork.domain.model.User

sealed class ConfirmationAccountEvent {

    data class EnteredCode(
        val value: String
    ) : ConfirmationAccountEvent()

    data class SaveUserDB(
        val user: User
    ) : ConfirmationAccountEvent()

    data class ConfirmationAccount(val email: String): ConfirmationAccountEvent()

}