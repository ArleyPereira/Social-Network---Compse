package com.example.socialnetwork.di

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceAPI {
        return serviceProvider.createService(ServiceAPI::class.java)
    }

}