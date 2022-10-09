package com.example.socialnetwork.domain.repository.api.user

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.util.BaseResponse

interface UserApiDataSource {

    suspend fun getUserById(userId: Long) : BaseResponse<UserDto>

    suspend fun getUsers() : BaseResponse<List<UserDto>>

}