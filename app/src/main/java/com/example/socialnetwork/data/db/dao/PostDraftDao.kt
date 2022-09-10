package com.example.socialnetwork.data.db.dao

import androidx.room.*
import com.example.socialnetwork.data.db.entity.PostDraftEntity

@Dao
interface PostDraftDao {

    @Query("SELECT * FROM post_draft_table")
    suspend fun getPost(): PostDraftEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postDraftEntity: PostDraftEntity) : Long

    @Query("DELETE FROM post_draft_table WHERE id = :id")
    suspend fun deletePost(id: Int = 0)

}