package com.example.socialnetwork.data.model

data class ErrorAPI(
    val data: Any? = null,
    val error: Boolean = false,
    val status: Int? = null,
    val message: String? = "",
    val action: String? = null
)
