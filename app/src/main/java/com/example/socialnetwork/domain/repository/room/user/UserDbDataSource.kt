package com.example.socialnetwork.domain.repository.room.user

import com.example.socialnetwork.data.db.entity.UserEntity

interface UserDbDataSource {

    suspend fun getUser(): UserEntity

    suspend fun insertUser(userEntity: UserEntity) : Long

    suspend fun deleteUser()

}