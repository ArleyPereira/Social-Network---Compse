package com.example.socialnetwork.presenter.bottombar.search.event

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User

sealed class SearchUIEvent {
    data class SearchSucess(val users: List<User>): SearchUIEvent()
    data class SearchError(val value: ErrorAPI): SearchUIEvent()

    object SearchLoading : SearchUIEvent()
}