package com.example.socialnetwork.presenter.auth.login.events

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.User

sealed class LoginUIEvent {
    data class LoginSucess(val user: User): LoginUIEvent()
    data class LoginError(val value: ErrorAPI): LoginUIEvent()

    object LoginLoading : LoginUIEvent()
}