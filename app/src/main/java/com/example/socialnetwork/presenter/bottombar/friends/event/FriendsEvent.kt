package com.example.socialnetwork.presenter.bottombar.friends.event

sealed class FriendsEvent {
    data class EnteredSearch(val value: String): FriendsEvent()

    object ClearTextSearch: FriendsEvent()
}