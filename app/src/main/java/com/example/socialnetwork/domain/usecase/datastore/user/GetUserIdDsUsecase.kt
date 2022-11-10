package com.example.socialnetwork.domain.usecase.datastore.user

import com.example.socialnetwork.data.repository.preference.user.UserDataStoreRepository
import javax.inject.Inject

class GetUserIdDsUsecase @Inject constructor(
    private val repository: UserDataStoreRepository
) {

    suspend operator fun invoke(): Long {
        return repository.getUserId()
    }

}