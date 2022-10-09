package com.example.socialnetwork.data.repository.preference.user

interface UserDataStoreRepository {

    suspend fun insertUserId(userId: Long)

    suspend fun getUserId(): Long

    suspend fun insertUserToken(token: String)

    suspend fun getUserToken(): String

}