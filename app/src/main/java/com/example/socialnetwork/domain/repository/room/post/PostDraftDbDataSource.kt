package com.example.socialnetwork.data.repository.room.post

import com.example.socialnetwork.data.db.entity.PostDraftEntity

interface PostDraftDbDataSource {

    suspend fun getPost(): PostDraftEntity

    suspend fun insertPost(postDraftEntity: PostDraftEntity) : Long

    suspend fun deletePost()

}