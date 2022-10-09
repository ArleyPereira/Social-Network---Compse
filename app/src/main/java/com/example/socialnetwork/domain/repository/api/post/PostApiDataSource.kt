package com.example.socialnetwork.data.repository.api.post

import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.util.BaseResponse

interface PostApiDataSource {

    suspend fun getPosts(): BaseResponse<List<Post>>

}