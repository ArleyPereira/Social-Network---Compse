package com.example.socialnetwork.data.repository.api.follow

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.domain.repository.api.follow.FollowApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class FollowApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : FollowApiDataSource {

    override suspend fun followUser(body: Map<String, String?>): BaseResponse<Unit> {
        return serviceAPI.followUser(body)
    }

}