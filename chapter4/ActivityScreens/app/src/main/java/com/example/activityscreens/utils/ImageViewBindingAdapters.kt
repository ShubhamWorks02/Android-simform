package com.example.activityscreens.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.activityscreens.R

@BindingAdapter("imageBind")
fun ImageView.setImageView(@DrawableRes id: Int?) {
    val drawable = if (id == null) {
        null
    } else {
        ContextCompat.getDrawable(context, id)
    }
    setImageDrawable(drawable)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView)
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .transform(CircleCrop())
            .into(imgView)
    }
}
