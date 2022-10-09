package com.example.socialnetwork.presenter.auth.confirmation.state

import com.example.socialnetwork.data.model.UserDto

data class ConfirmationAccountState(
    private val userDto: UserDto? = null,
    private val isLoading: Boolean = false,
    private val error: String? = null,
    private val textButtonResend: String = "",
    private val isTimeRunning: Boolean = true
)
