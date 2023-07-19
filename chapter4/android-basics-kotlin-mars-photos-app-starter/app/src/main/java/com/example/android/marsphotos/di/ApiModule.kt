package com.example.android.marsphotos.di

import com.example.android.marsphotos.data.remote.MarsApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): MarsApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com")
        .build()
        .create(MarsApiService::class.java)
}
