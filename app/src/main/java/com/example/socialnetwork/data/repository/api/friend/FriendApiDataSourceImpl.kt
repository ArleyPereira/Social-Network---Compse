package com.example.socialnetwork.data.repository.api.friend

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.domain.repository.api.friend.FriendApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class FriendApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : FriendApiDataSource {

    override suspend fun getFriends(): BaseResponse<List<UserDto>> {
        return serviceAPI.getFriends()
    }

}