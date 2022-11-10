package com.example.socialnetwork.util

data class BaseResponse<T>(
    val action: String? = null,
    val data: T? = null,
    val status: Int? = null,
    val message: String? = null,
    val error: Boolean? = null,
)