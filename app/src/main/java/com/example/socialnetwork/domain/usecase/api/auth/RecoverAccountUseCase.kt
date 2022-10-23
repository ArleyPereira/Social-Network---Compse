package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.domain.repository.api.auth.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class RecoverAccountUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(code: String, token: String): BaseResponse<Unit> {
        return repository.recoverAccount(code, token)
    }

}