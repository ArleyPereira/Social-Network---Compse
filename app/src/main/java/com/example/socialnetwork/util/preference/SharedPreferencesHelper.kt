package com.example.socialnetwork.util.preference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_preference", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "token"
    }

    fun saveToken(token: String?) {
        val editor = preferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String {
        return preferences.getString(TOKEN_KEY, "") ?: ""
    }

}