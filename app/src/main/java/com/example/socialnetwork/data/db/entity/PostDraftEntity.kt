package com.example.socialnetwork.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.socialnetwork.data.model.PostDraft

@Entity(tableName = "post_draft_table")
data class PostDraftEntity(
    @PrimaryKey
    val id: Int? = 0,
    val idUser: Long? = null,
    val image: String? = null,
    val description: String = ""
)

fun PostDraft.toPostDraftEntity(): PostDraftEntity {
    return with(this) {
        PostDraftEntity(
            id = this.id,
            idUser = this.idUser,
            image = this.image,
            description = this.description
        )
    }
}

fun PostDraftEntity.toPostDraft(): PostDraft {
    return with(this) {
        PostDraft(
            idUser = this.idUser,
            image = this.image,
            description = this.description
        )
    }
}
