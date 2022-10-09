package com.example.socialnetwork.domain.usecase.datastore.user

import com.example.socialnetwork.data.repository.preference.user.UserDataStoreRepository
import javax.inject.Inject

class GetUserTokenUsecase @Inject constructor(
    private val repository: UserDataStoreRepository
) {

    suspend operator fun invoke(): String {
        return repository.getUserToken()
    }

}