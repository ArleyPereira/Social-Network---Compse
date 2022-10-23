package com.example.socialnetwork.domain.usecase.api.post

import com.example.socialnetwork.data.model.PostDto
import com.example.socialnetwork.domain.repository.api.post.PostApiDataSource
import com.example.socialnetwork.util.BaseResponse
import com.google.gson.JsonObject
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostApiDataSource
) {

    suspend operator fun invoke(
        body: JsonObject
    ): BaseResponse<List<PostDto>> {
        return repository.getPosts(body)
    }

}