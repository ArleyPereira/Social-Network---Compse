package com.example.socialnetwork.domain.repository.api.post

import com.example.socialnetwork.data.model.PostDto
import com.example.socialnetwork.util.BaseResponse
import com.google.gson.JsonObject

interface PostApiDataSource {

    suspend fun getPosts(
        body: JsonObject
    ): BaseResponse<List<PostDto>>

}