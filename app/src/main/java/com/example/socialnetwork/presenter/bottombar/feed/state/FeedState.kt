package com.example.socialnetwork.presenter.bottombar.feed.state

import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.model.Post

data class FeedState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: ErrorAPI? = null
)
