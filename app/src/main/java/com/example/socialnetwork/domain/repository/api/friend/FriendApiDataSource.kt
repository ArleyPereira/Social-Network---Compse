package com.example.socialnetwork.domain.repository.api.friend

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.util.BaseResponse

interface FriendApiDataSource {

    suspend fun getFriends(): BaseResponse<List<UserDto>>

}