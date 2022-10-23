package com.example.socialnetwork.domain.repository.api.follow

import com.example.socialnetwork.util.BaseResponse

interface FollowApiDataSource {

    suspend fun followUser(
        body: Map<String, String?>
    ) : BaseResponse<Unit>

}