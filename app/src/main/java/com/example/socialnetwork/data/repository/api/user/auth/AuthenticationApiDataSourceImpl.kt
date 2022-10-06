package com.example.socialnetwork.data.repository.api.user.auth

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class AuthenticationApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : AuthenticationApiDataSource {

    override suspend fun register(user: Map<String, String>): BaseResponse<User> {
        return serviceAPI.register(user)
    }

    override suspend fun login(body: Map<String, String>): BaseResponse<User> {
        return serviceAPI.login(body)
    }

    override suspend fun emailConfirm(body: Map<String, String>): BaseResponse<Unit> {
        return serviceAPI.emailConfirm(body)
    }

    override suspend fun recover(code: String, token: String): BaseResponse<Unit> {
        return serviceAPI.recover(code, token)
    }

    override suspend fun logout(): BaseResponse<User> {
        return serviceAPI.logout()
    }

}