package com.example.socialnetwork.data.repository.api.post

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.PostDto
import com.example.socialnetwork.domain.repository.api.post.PostApiDataSource
import com.example.socialnetwork.util.BaseResponse
import com.google.gson.JsonObject
import javax.inject.Inject

class PostApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : PostApiDataSource {

    override suspend fun getPosts(
        body: JsonObject
    ): BaseResponse<List<PostDto>> {
        return serviceAPI.getPosts(body)
    }

}