package com.vapetrosyan.flowrspot.di

import com.vapetrosyan.flowrspot.data.FlowerRepository
import com.vapetrosyan.flowrspot.data.api.FlowrSpotApi
import com.vapetrosyan.flowrspot.data.converter.FlowerListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FlowersDataProvider {
    @Provides
    @Singleton
    fun provideFlowerRepository(
        api: FlowrSpotApi,
        flowerListConverter: FlowerListConverter
    ): FlowerRepository {
        return FlowerRepository(api, flowerListConverter)
    }
}