package com.example.socialnetwork.presenter.auth.comfirmation.event

sealed class ConfirmationAccountEvent {
    data class EnteredCode(val value: String): ConfirmationAccountEvent()
}