package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.domain.repository.api.user.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<UserDto> {
        return repository.login(body)
    }

}