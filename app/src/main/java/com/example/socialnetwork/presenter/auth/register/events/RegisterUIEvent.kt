package com.example.socialnetwork.presenter.auth.register.events

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User

sealed class RegisterUIEvent {
    data class RegisterSucess(val user: User): RegisterUIEvent()
    data class RegisterError(val value: ErrorAPI): RegisterUIEvent()

    object RegisterLoading : RegisterUIEvent()
}
