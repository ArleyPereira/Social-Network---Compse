package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.data.repository.api.user.profile.ProfileApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class EmailConfirmationUsecase @Inject constructor(
    private val repository: ProfileApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.emailConfirmUpdate(body)
    }

}