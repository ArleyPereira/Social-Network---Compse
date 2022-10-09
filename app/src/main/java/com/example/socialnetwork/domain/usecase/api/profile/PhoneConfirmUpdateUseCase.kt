package com.example.socialnetwork.domain.usecase.api.profile

import com.example.socialnetwork.domain.repository.api.user.profile.ProfileApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class PhoneConfirmUpdateUseCase @Inject constructor(
    private val repository: ProfileApiDataSource
) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.phoneConfirmUpdate(body)
    }

}