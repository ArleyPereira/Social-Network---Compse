package com.example.socialnetwork.util

import com.google.gson.Gson
import retrofit2.HttpException

// Retorna o errorBody em forma de um objeto
inline fun <reified T> HttpException.getErrorResponse(): T? {
    return try {
        Gson().fromJson(response()?.errorBody()?.string(), T::class.java)
    } catch (e: Exception) {
        null
    }
}

/**
 * @author Arley Santana
 * @return[String] Ex: 1995-04-28
 * Recebe a data em formato BR (28/04/1995) e retorna formato EUA (1995-04-28)
 */
fun String.convertDateBirth(): String {
    val date = this.split("-")
    return date[2].plus("-").plus(date[1]).plus("-").plus(date[0])
}