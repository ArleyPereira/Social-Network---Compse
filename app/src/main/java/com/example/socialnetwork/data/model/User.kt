package com.example.socialnetwork.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("nick_name")
    val nickName: String? = null,
    @SerializedName("date_birth")
    val dateBirth: String? = null,
    val document: String? = null,
    val genre: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val avatar: String? = null,
    val token: String? = null
) : Parcelable