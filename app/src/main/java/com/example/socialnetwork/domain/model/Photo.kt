package com.example.socialnetwork.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: Int? = null,
    val file: String? = null,
    val galleryId: Long? = null,
    val postId: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) : Parcelable