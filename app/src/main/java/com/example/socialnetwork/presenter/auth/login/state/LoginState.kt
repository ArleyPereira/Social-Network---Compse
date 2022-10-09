package com.example.socialnetwork.presenter.auth.login.state

import com.example.socialnetwork.data.model.UserDto

data class LoginState(
    private val userDto: UserDto? = null,
    private val isLoading: Boolean = false,
    private val error: String? = null
)
