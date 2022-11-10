package com.example.socialnetwork.presenter.bottombar.search.event

sealed class SearchEvent {

    data class EnteredSearch(
        val value: String
    ) : SearchEvent()

    data class FollowUser(
        val userId: Long,
        val followedId: Long
    ) : SearchEvent()

    object ClearTextSearch : SearchEvent()
    object ClickedButtonSheetError : SearchEvent()

}