package com.example.socialnetwork.domain.repository.api.user

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.util.BaseResponse

interface ProfileApiDataSource {

    suspend fun getUserById(userId: Long) : BaseResponse<UserDto>

    suspend fun phoneUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun emailUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun phoneConfirmUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun emailConfirmUpdate(body: Map<String, String?>) : BaseResponse<Unit>

}