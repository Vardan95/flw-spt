package com.vapetrosyan.flowrspot.di

import com.vapetrosyan.flowrspot.data.converter.FlowerListConverter
import com.vapetrosyan.flowrspot.data.converter.FlowerListItemConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class FlowersModelConverterProvider {

    @Provides
    fun provideFlowerListItemConverter(): FlowerListItemConverter {
        return FlowerListItemConverter()
    }

    @Provides
    fun provideFlowerListConverter(flowerListItemConverter: FlowerListItemConverter): FlowerListConverter {
        return FlowerListConverter(flowerListListItemConverter = flowerListItemConverter)
    }
}