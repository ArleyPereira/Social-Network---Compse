package com.example.socialnetwork.presenter.auth.comfirmation.state

import com.example.socialnetwork.data.model.User

data class ConfirmationAccountState(
    private val user: User? = null,
    private val isLoading: Boolean = false,
    private val error: String? = null,
    private val textButtonResend: String = "",
    private val isTimeRunning: Boolean = true
)
