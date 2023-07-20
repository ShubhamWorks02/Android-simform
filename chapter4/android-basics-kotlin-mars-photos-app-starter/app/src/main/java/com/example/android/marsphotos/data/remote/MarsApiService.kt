package com.example.android.marsphotos.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun  getPhotos(): List<MarsPhoto>
}

data class MarsPhoto(
    @SerializedName("id")
    val photoId: String,
    @SerializedName("img_src")
    val photoUrl: String
)
