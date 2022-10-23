package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.domain.repository.api.auth.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class ConfirmationAccountUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.confirmationAccount(body)
    }

}