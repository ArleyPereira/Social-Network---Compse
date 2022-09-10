package com.example.socialnetwork.domain.usecase.room.user

import com.example.socialnetwork.data.db.entity.UserEntity
import com.example.socialnetwork.data.repository.room.user.UserDbDataSource
import javax.inject.Inject

class GetUserDbUsecase @Inject constructor(
    private val repository: UserDbDataSource
) {

    suspend operator fun invoke(): UserEntity {
        return repository.getUser()
    }

}