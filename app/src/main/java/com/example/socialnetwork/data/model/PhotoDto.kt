package com.example.socialnetwork.data.model

import android.os.Parcelable
import com.example.socialnetwork.domain.model.Photo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDto(
    val id: Int? = null,
    val file: String? = null,
    @SerializedName("gallery_id")
    val galleryId: Long? = null,
    @SerializedName("post_id")
    val postId: Int? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
) : Parcelable

fun PhotoDto.toDomain(): Photo {
    return Photo(
        id = id,
        file = file,
        galleryId = galleryId,
        postId = postId,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}