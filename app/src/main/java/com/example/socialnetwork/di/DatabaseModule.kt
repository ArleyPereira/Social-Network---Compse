package com.example.socialnetwork.di

import android.content.Context
import androidx.room.Room
import com.example.socialnetwork.R
import com.example.socialnetwork.data.db.AppDatabase
import com.example.socialnetwork.data.db.dao.PostDraftDao
import com.example.socialnetwork.data.db.dao.UserDao
import com.example.socialnetwork.util.datastore.UserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            appContext.getString(R.string.app_name_database)
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePostDraftDao(appDatabase: AppDatabase): PostDraftDao {
        return appDatabase.postDraftDao()
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): UserDataStore {
        return UserDataStore(context)
    }

}