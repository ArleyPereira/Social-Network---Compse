package com.example.socialnetwork.presenter.bottombar.search.event

sealed class SearchEvent {
    data class EnteredSearch(val value: String): SearchEvent()

    object ClearTextSearch: SearchEvent()
}