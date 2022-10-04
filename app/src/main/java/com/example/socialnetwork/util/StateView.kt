package com.example.socialnetwork.util

sealed class StateView<T>(
    val data: T? = null,
    val message: String? = null,
    val action: String? = null
) {
    class Loading<T> : StateView<T>(data = null, message = null)

    class Error<T>(message: String?, action: String? = null) :
        StateView<T>(message = message, action = action)

    class Sucess<T>(data: T, message: String? = null) : StateView<T>(data = data, message = message)
}