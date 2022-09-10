package com.example.socialnetwork.domain.usecase.datastore.user

import com.example.socialnetwork.data.repository.datastore.user.UserDataStoreRepository
import javax.inject.Inject

class InsertUserIdDsUsecase @Inject constructor(
    private val repository: UserDataStoreRepository
) {

    suspend operator fun invoke(userId: Long) {
        return repository.insertUserId(userId)
    }

}