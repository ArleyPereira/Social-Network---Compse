package com.example.socialnetwork.domain.usecase.api.user

import com.example.socialnetwork.domain.repository.api.user.ProfileApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class EmailUpdateUseCase @Inject constructor(
    private val repository: ProfileApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.emailUpdate(body)
    }

}