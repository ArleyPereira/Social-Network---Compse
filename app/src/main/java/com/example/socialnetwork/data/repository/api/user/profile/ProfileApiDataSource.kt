package com.example.socialnetwork.data.repository.api.user.profile

import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.util.BaseResponse

interface ProfileApiDataSource {

    suspend fun getProfile(userId: Long) : BaseResponse<User>

    suspend fun phoneUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun emailUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun phoneConfirmUpdate(body: Map<String, String>) : BaseResponse<Unit>

    suspend fun emailConfirmUpdate(body: Map<String, String>) : BaseResponse<Unit>

}