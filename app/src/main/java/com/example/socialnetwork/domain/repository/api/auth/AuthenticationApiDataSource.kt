package com.example.socialnetwork.domain.repository.api.auth

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.util.BaseResponse

interface AuthenticationApiDataSource {

    suspend fun register(user: Map<String, String>): BaseResponse<UserDto>

    suspend fun login(body: Map<String, String>): BaseResponse<UserDto>

    suspend fun confirmationAccount(body: Map<String, String>): BaseResponse<Unit>

    suspend fun recoverAccount(code: String, token: String): BaseResponse<Unit>

    suspend fun logout(): BaseResponse<UserDto>

}