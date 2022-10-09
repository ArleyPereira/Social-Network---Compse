package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.domain.repository.api.user.auth.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class RecoverUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(code: String, token: String): BaseResponse<Unit> {
        return repository.recover(code, token)
    }

}