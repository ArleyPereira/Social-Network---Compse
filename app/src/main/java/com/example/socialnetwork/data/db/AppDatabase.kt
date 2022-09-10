package com.example.socialnetwork.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.socialnetwork.R
import com.example.socialnetwork.data.db.dao.PostDraftDao
import com.example.socialnetwork.data.db.dao.UserDao
import com.example.socialnetwork.data.db.entity.PostDraftEntity
import com.example.socialnetwork.data.db.entity.UserEntity

@Database(entities = [UserEntity::class, PostDraftEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDraftDao(): PostDraftDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    context.getString(R.string.app_name_database)
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}