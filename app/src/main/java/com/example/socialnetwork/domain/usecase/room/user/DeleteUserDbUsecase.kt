package com.example.socialnetwork.domain.usecase.room.user

import com.example.socialnetwork.data.repository.room.user.UserDbDataSource
import javax.inject.Inject

class DeleteUserDbUsecase @Inject constructor(
    private val repository: UserDbDataSource
) {

    suspend operator fun invoke(id: Long) {
        return repository.deleteUser(id)
    }

}