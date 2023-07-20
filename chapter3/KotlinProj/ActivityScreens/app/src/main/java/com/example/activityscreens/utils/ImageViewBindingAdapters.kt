package com.example.activityscreens.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun ImageView.setImageView(@DrawableRes id: Int) {
    setImageDrawable(
        ContextCompat.getDrawable(context, id)
    )
}
