package com.example.activityscreens.data.remote.response

import com.google.gson.annotations.SerializedName

data class User(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<UserDetails>,
    val support: Support,
)
