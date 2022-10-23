package com.example.socialnetwork.presenter.bottombar.search.state

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.User

data class SearchState(
    val userLocal: User? = null,
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: ErrorAPI? = null
)
