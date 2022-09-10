package com.example.socialnetwork.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDraft(
    val id: Int? = 0,
    val idUser: Long? = null,
    val image: String? = null,
    val description: String = ""
) : Parcelable