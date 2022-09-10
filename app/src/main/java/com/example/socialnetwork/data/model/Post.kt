package com.example.socialnetwork.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Post(
    val id: Long? = null,
    @SerialName("id_profile")
    val idProfile: Long? = null,
    @SerialName("url_profile")
    val urlProfile: String? = null,
    @SerialName("name_profile")
    val nameProfile: String? = null,
    val place: String? = null,
    val image: String? = null,
    val description: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null
) : Parcelable