package com.example.socialnetwork.util.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val context: Context
) {

    private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "USER_DATASTORE")

    companion object {
        private val USER_ID_KEY = longPreferencesKey("USER_ID")
        private val USER_TOKEN_KEY = stringPreferencesKey("USER_TOKEN_KEY")
    }

    suspend fun insertUserId(userId: Long) {
        context.dataUser.edit {
            it[USER_ID_KEY] = userId
        }
    }

    suspend fun getUserId(): Long {
        val prefs = context.dataUser.data.first()
        return prefs[USER_ID_KEY] ?: 0
    }

    suspend fun insertUserToken(token: String) {
        context.dataUser.edit {
            it[USER_TOKEN_KEY] = token
        }
    }

    suspend fun getUserToken(): String {
        val prefs = context.dataUser.data.first()
        return prefs[USER_TOKEN_KEY] ?: ""
    }

}