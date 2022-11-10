package com.example.socialnetwork.domain.model

import android.os.Parcelable
import com.example.socialnetwork.data.db.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val nickName: String? = null,
    val dateBirth: String? = null,
    val document: String? = null,
    val genre: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val avatar: String? = null,
    val token: String? = null
) : Parcelable

fun User.toUserEntity(): UserEntity {
    return with(this) {
        UserEntity(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            dateBirth = this.dateBirth,
            document = this.document,
            genre = this.genre,
            email = this.email,
            phone = this.phone,
            password = this.password,
            avatar = this.avatar,
            token = this.token
        )
    }
}