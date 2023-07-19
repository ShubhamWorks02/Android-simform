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

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.androidwebservice.BuildConfig
import com.example.androidwebservice.utils.FlavorDelegate
import com.example.androidwebservice.utils.ProductFlavor
import com.example.androidwebservice.utils.ProductFlavor.Flavor

@BindingAdapter("showFlavorInfo", "flavorDelegate")
fun showFlavorInfo(view: TextView, flavor: Flavor?, delegate: FlavorDelegate) {
    if (flavor == null) {
        view.isVisible = false
        return
    }

    if (ProductFlavor.CURRENT == Flavor.DEV || ProductFlavor.CURRENT == Flavor.QA) {
        val message = "Environment: ${flavor.name}\n${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
        view.text = message
        view.isVisible = true
        val allFlavor = Flavor::class.sealedSubclasses.mapNotNull { it.objectInstance }
        val flavors = allFlavor.map { it.name }.toTypedArray()
        view.setOnClickListener {
            MaterialAlertDialogBuilder(view.context)
                .setSingleChoiceItems(flavors, allFlavor.indexOf(flavor)) { dialog, which ->
                    val newFlavor = allFlavor[which]
                    delegate.setFlavor(newFlavor)
                    dialog.dismiss()
                }
                .show()
        }
    } else {
        view.isVisible = false
    }
}
