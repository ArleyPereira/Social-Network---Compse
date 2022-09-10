package com.example.socialnetwork.data.repository.room.post

import com.example.socialnetwork.data.db.dao.PostDraftDao
import com.example.socialnetwork.data.db.entity.PostDraftEntity
import javax.inject.Inject

class PostDraftDbDataSourceImpl @Inject constructor(
    private val postDraftDao: PostDraftDao
) : PostDraftDbDataSource {

    override suspend fun getPost(): PostDraftEntity {
        return postDraftDao.getPost()
    }

    override suspend fun insertPost(postDraftEntity: PostDraftEntity): Long {
        return postDraftDao.insertPost(postDraftEntity)
    }

    override suspend fun deletePost() {
        postDraftDao.deletePost()
    }

}