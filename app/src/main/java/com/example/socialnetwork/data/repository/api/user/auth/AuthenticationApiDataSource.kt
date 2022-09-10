package com.example.socialnetwork.data.repository.api.user.auth

import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.util.BaseResponse

interface AuthenticationApiDataSource {

    suspend fun register(user: Map<String, String>): BaseResponse<User>

    suspend fun login(body: Map<String, String>): BaseResponse<User>

    suspend fun emailConfirm(body: Map<String, String>): BaseResponse<Unit>

    suspend fun recover(body: Map<String, String>): BaseResponse<Unit>

    suspend fun logout(): BaseResponse<User>

}