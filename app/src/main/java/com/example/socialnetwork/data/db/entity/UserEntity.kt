package com.example.socialnetwork.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.socialnetwork.data.model.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo(name = "first_name")
    val firstName: String? = null,
    @ColumnInfo(name = "last_name")
    val lastName: String? = null,
    @ColumnInfo(name = "date_birth")
    val dateBirth: String? = null,
    val document: String? = null,
    val genre: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val avatar: String? = null,
    val token: String? = null
)

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

fun UserEntity.toUser(): User {
    return with(this) {
        User(
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