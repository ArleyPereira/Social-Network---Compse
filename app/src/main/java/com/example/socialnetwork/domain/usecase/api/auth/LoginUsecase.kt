package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.data.repository.api.user.auth.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class LoginUsecase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<User> {
        return repository.login(body)
    }

}