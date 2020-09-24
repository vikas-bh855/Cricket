package com.sportsinteractive.cricket

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule
{
    @Singleton
    @Provides
    fun provideApiServie(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://cricket.yahoo.net/sifeeds/cricket/live/json/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }
}