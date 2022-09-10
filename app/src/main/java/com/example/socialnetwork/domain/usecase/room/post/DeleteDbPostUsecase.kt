package com.example.socialnetwork.domain.usecase.room.post

import com.example.socialnetwork.data.repository.room.post.PostDraftDbDataSource
import javax.inject.Inject

class DeleteDbPostUsecase @Inject constructor(
    private val repository: PostDraftDbDataSource
) {

    suspend operator fun invoke() {
        return repository.deletePost()
    }

}