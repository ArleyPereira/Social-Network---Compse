package com.example.socialnetwork.data.model

data class ErrorAPI(
    val error: Boolean? = null,
    val status: Int? = null,
    val message: String? = null,
    val action: String? = null
)
