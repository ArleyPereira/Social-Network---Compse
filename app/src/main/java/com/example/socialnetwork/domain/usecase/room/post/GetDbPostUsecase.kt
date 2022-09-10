package com.example.socialnetwork.domain.usecase.room.post

import com.example.socialnetwork.data.db.entity.PostDraftEntity
import com.example.socialnetwork.data.repository.room.post.PostDraftDbDataSource
import javax.inject.Inject

class GetDbPostUsecase @Inject constructor(
    private val repository: PostDraftDbDataSource
) {

    suspend operator fun invoke(): PostDraftEntity {
        return repository.getPost()
    }

}