package com.example.socialnetwork.domain.usecase.api.post

import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.data.repository.api.post.PostApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostApiDataSource
) {

    suspend operator fun invoke(): BaseResponse<List<Post>> {
        return repository.getPosts()
    }

}