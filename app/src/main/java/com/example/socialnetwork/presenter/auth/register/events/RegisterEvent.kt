package com.example.socialnetwork.presenter.auth.register.events

sealed class RegisterEvent {
    data class EnteredFirstName(val value: String) : RegisterEvent()
    data class EnteredLastName(val value: String) : RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredBirth(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()

    object Register : RegisterEvent()
}
