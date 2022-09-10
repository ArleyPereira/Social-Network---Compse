package com.example.socialnetwork.data.repository.api.post

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class PostApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : PostApiDataSource {

    override suspend fun getPosts(): BaseResponse<List<Post>> {
        return serviceAPI.getPosts()
    }

}