package com.example.tillin.di

import com.example.tillin.data.remote.OpenAIService
import dagger.Module             // 이게 빠졌을 확률이 높아요!
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOpenAIService(): OpenAIService {
        return OpenAIService("OPEN_API_KEY")
    }
}