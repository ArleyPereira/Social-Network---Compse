package com.example.socialnetwork.domain.usecase.room.user

import com.example.socialnetwork.domain.repository.room.user.UserDbDataSource
import javax.inject.Inject

class DeleteUserDbUseCase @Inject constructor(
    private val repository: UserDbDataSource
) {

    suspend operator fun invoke() {
        return repository.deleteUser()
    }

}