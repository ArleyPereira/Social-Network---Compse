package com.example.socialnetwork.di

import com.example.socialnetwork.data.repository.api.friend.FriendApiDataSource
import com.example.socialnetwork.data.repository.api.friend.FriendApiDataSourceImpl
import com.example.socialnetwork.data.repository.api.post.PostApiDataSource
import com.example.socialnetwork.data.repository.api.post.PostApiDataSourceImpl
import com.example.socialnetwork.domain.repository.api.user.auth.AuthenticationApiDataSource
import com.example.socialnetwork.data.repository.api.user.auth.AuthenticationApiDataSourceImpl
import com.example.socialnetwork.domain.repository.api.user.profile.ProfileApiDataSource
import com.example.socialnetwork.data.repository.api.user.profile.ProfileApiDataSourceImpl
import com.example.socialnetwork.data.repository.preference.user.UserDataStoreRepository
import com.example.socialnetwork.data.repository.preference.user.UserDataStoreRepositoryImpl
import com.example.socialnetwork.data.repository.room.post.PostDraftDbDataSource
import com.example.socialnetwork.data.repository.room.post.PostDraftDbDataSourceImpl
import com.example.socialnetwork.data.repository.room.user.UserDbDataSource
import com.example.socialnetwork.data.repository.room.user.UserDbDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFriendApiDataSource(
        friendApiDataSourceImpl: FriendApiDataSourceImpl
    ): FriendApiDataSource

    @Binds
    abstract fun bindsPostApiDataSource(
        postApiDataSourceImpl: PostApiDataSourceImpl
    ): PostApiDataSource

    @Binds
    abstract fun bindsPostDraftDbDataSource(
        postDraftDbDataSourceImpl: PostDraftDbDataSourceImpl
    ): PostDraftDbDataSource

    @Binds
    abstract fun bindsUserDbDataSource(
        userDbDataSourceImpl: UserDbDataSourceImpl
    ): UserDbDataSource

    @Binds
    abstract fun bindsAuthApiDataSource(
        authenticationApiDataSourceImpl: AuthenticationApiDataSourceImpl
    ): AuthenticationApiDataSource

    @Binds
    abstract fun bindsProfileApiDataSource(
        profileApiDataSourceImpl: ProfileApiDataSourceImpl
    ): ProfileApiDataSource

    @Binds
    abstract fun bindsUserDataStore(
        userDataStoreRepositoryImpl: UserDataStoreRepositoryImpl
    ): UserDataStoreRepository

}