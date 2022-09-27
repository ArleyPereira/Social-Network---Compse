package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.data.repository.api.user.auth.AuthenticationApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class RecoverUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.recover(body)
    }

}