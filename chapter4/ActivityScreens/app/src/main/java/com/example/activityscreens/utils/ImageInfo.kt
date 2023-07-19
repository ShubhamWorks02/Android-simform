package com.example.activityscreens.utils

import com.example.activityscreens.R

data class ImageInfo(val id: Int)

object ImageData {
    val imageList = listOf(
        ImageInfo(R.drawable.img_search_top), ImageInfo(R.drawable.img_setting),
        ImageInfo(R.drawable.img_voice_mail)
    )
}