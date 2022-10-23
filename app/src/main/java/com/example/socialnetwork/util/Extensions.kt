package com.example.socialnetwork.util

import com.google.gson.Gson
import retrofit2.HttpException
import java.util.*

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

fun Date.getPrettyPastTime(): String {
    val today = Date()
    val diff = today.time - this.time
    val days = diff / (24 * 60 * 60 * 1000)
    val hours = diff / (60 * 60 * 1000)
    val minutes = diff / (60 * 1000)
    return if (days > 365) {
        if (days / 365 < 2)
            "Há 1 ano atrás"
        else
            "Há ${days / 365} anos atrás"

    } else if (days in 30..365) {
        if (days / 30 < 2)
            "Há 1 mês atrás"
        else
            "Há ${days / 30} meses atrás"
    } else if (days in 7..29) {
        if (days < 15)
            "Há 1 semana atrás"
        else
            "${days / 7} semanas atrás"
    } else if (days in 1..6) {
        if (days > 1)
            "Há $days dias atrás"
        else
            "Há 1 dia atrás"
    } else if (hours > 0) {
        if (hours > 1)
            "Há $hours horas atrás"
        else
            "Há 1 hora atrás"
    } else if (minutes > 0) {
        if (minutes > 1)
            "Há $minutes minutos atrás"
        else
            "Há 1 minuto atrás"
    } else {
        "Agora"
    }
}