package com.example.socialnetwork.data.repository.preference.user

import com.example.socialnetwork.util.datastore.UserDataStore
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : UserDataStoreRepository {

    override suspend fun insertUserId(userId: Long) {
        userDataStore.insertUserId(userId)
    }

    override suspend fun getUserId(): Long {
        return userDataStore.getUserId()
    }

    override suspend fun insertUserToken(token: String) {
        userDataStore.insertUserToken(token)
    }

    override suspend fun getUserToken(): String {
        return userDataStore.getUserToken()
    }

}