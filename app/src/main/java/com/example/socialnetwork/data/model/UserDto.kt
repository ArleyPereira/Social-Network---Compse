package com.example.socialnetwork.data.model

import android.os.Parcelable
import com.example.socialnetwork.data.db.entity.UserEntity
import com.example.socialnetwork.domain.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
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

fun UserDto.toDomain() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    nickName = nickName,
    dateBirth = dateBirth,
    document = document,
    genre = genre,
    email = email,
    phone = phone,
    password = password,
    avatar = avatar,
    token = token
)