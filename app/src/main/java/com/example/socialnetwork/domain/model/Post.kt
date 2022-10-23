package com.example.socialnetwork.domain.model

import android.os.Parcelable
import com.example.socialnetwork.data.model.PhotoDto
import com.example.socialnetwork.data.model.UserDto
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import java.util.*

@Parcelize
data class Post(
    val id: Long? = null,
    val userId: Long? = null,
    val user: User? = null,
    val photos: List<Photo> = listOf(),
    val place: String? = null,
    val image: String? = null,
    val content: String? = null,
    val updatedAt: Date? = null,
    val createdAt: Date? = null
) : Parcelable