package com.example.socialnetwork.data.repository.room.user

import com.example.socialnetwork.data.db.dao.UserDao
import com.example.socialnetwork.data.db.entity.UserEntity
import com.example.socialnetwork.domain.repository.room.user.UserDbDataSource
import javax.inject.Inject

class UserDbDataSourceImpl @Inject constructor(
    private val userDAO: UserDao
) : UserDbDataSource {

    override suspend fun getUser(): UserEntity {
        return userDAO.getUser()
    }

    override suspend fun insertUser(userEntity: UserEntity): Long {
        return userDAO.insertUser(userEntity)
    }

    override suspend fun deleteUser() {
        userDAO.deleteUser()
    }

}