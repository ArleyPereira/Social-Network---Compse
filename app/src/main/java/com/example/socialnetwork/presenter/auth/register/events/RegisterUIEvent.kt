package com.example.socialnetwork.presenter.auth.register.events

import com.example.socialnetwork.data.model.User

sealed class RegisterUIEvent {
    data class RegisterSucess(val user: User): RegisterUIEvent()
    data class RegisterError(val message: String): RegisterUIEvent()

    object RegisterLoading : RegisterUIEvent()
}
