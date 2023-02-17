package com.example.android.marsphotos.data.repository

import com.example.android.marsphotos.data.remote.MarsApiService
import com.example.android.marsphotos.data.remote.MarsPhoto
import javax.inject.Inject
import javax.inject.Singleton

interface PhotoRepository {
    suspend fun getPhotos(): List<MarsPhoto>
}

@Singleton
class PhotoRepositoryimpl @Inject constructor (private val apiService: MarsApiService): PhotoRepository {
    override suspend fun getPhotos(): List<MarsPhoto> =
        apiService.getPhotos()
}