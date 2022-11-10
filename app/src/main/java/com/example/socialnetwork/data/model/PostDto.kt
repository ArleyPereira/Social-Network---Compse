package com.example.socialnetwork.data.model

import android.os.Parcelable
import com.example.socialnetwork.domain.model.Post
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import java.util.*

@Parcelize
data class PostDto(
    val id: Long? = null,
    @SerializedName("user_id")
    val userId: Long? = null,
    val user: UserDto? = null,
    val photos: List<PhotoDto> = listOf(),
    val place: String? = null,
    val image: String? = null,
    val content: String? = null,
    @SerializedName("updated_at")
    val updatedAt: Date? = null,
    @SerializedName("created_at")
    val createdAt: Date? = null
) : Parcelable

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        userId = userId,
        user = user?.toDomain(),
        photos = photos.map { it.toDomain() },
        place = place,
        image = image,
        content = content,
        updatedAt = updatedAt,
        createdAt = createdAt
    )
}