package com.example.android.marsphotos.di

import com.example.android.marsphotos.data.repository.PhotoRepository
import com.example.android.marsphotos.data.repository.PhotoRepositoryimpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: PhotoRepositoryimpl): PhotoRepository
}
