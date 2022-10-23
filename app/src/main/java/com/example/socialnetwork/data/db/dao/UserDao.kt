package com.example.socialnetwork.data.db.dao

import androidx.room.*
import com.example.socialnetwork.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE id_local = 0")
    suspend fun getUser(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity) : Long

    @Query("DELETE FROM user_table WHERE id_local = 0")
    suspend fun deleteUser()

}