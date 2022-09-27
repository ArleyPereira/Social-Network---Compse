package com.example.socialnetwork.presenter.auth.login.events

import com.example.socialnetwork.data.model.User

sealed class LoginUIEvent {
    data class LoginSucess(val user: User): LoginUIEvent()
    data class LoginError(val message: String): LoginUIEvent()
    object LoginLoading : LoginUIEvent()
}
