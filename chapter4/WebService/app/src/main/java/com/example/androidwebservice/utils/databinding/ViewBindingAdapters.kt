/**
 * Copyright COPYRIGHT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androidwebservice.utils.databinding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Set view's visibility gone.
 * @param isGone true to make view gone
 */
@BindingAdapter("showUnlessGone")
fun showUnlessGone(view: View, isGone: Boolean) {
    view.isGone = isGone
}

/**
 * Set view's visibility invisible.
 * @param isInvisible true to make view invisible
 */
@BindingAdapter("showUnlessInvisible")
fun showUnlessInvisible(view: View, isInvisible: Boolean) {
    view.isInvisible = isInvisible
}

/**
 * Load image into [imageView] from the [imageUri].
 */
@BindingAdapter(value = ["imageUri", "placeholder"], requireAll = false)
fun imageUri(imageView: ImageView, imageUri: Uri?, placeholder: Drawable?) {
    Glide.with(imageView)
        .load(imageUri)
        .placeholder(placeholder)
        .into(imageView)
}

/**
 * Load image into [imageView] from the [imageUrl].
 */
@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun imageUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable?) {
    imageUri(imageView, imageUrl?.toUri(), placeholder)
}
