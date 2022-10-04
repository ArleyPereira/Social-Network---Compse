package com.example.socialnetwork.presenter.auth.login.state

import com.example.socialnetwork.data.model.User

data class LoginState(
    private val user: User? = null,
    private val isLoading: Boolean = false,
    private val error: String? = null
)
