package com.example.socialnetwork.domain.usecase.api.user.update.phone

import com.example.socialnetwork.data.repository.api.user.profile.ProfileApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class PhoneConfirmationUseCase @Inject constructor(private val repository: ProfileApiDataSource) {

    suspend operator fun invoke(body: Map<String, String>): BaseResponse<Unit> {
        return repository.phoneConfirmUpdate(body)
    }

}