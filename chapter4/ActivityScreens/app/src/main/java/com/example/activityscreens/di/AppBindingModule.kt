package com.example.activityscreens.di

import com.example.activityscreens.data.repository.UserRepository
import com.example.activityscreens.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
abstract class AppBindingModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
