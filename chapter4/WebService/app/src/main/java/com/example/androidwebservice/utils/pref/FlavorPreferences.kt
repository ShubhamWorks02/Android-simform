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
package com.example.androidwebservice.utils.pref

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidwebservice.BuildConfig
import com.example.androidwebservice.utils.ProductFlavor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Shared preference delegated property for product flavor. Use this class to change
 * product flavor runtime.
 * Usage:
 * ```
 * // Lazy variable for shared preference
 * val lazySharedPreferences = lazy { getSharedPreferences(...) }
 * // Delegated property
 * var productFlavor by FlavorPreferencesImpl.FlavorPreference(
 *     lazySharedPrefs,
 *     ProductFlavor.Flavor.PRODUCTION.name,
 *     defaultProductFlavor
 * )
 *
 * // Set value
 * productFlavor = ProductFlavor.Flavor.QA
 * // Get value
 * if (productFlavor == ProductFlavor.Flavor.PRODUCTION)
 *     fetchProductionApi()
 * else if (productFlavor == ProductFlavor.Flavor.QA)
 *     fetchQaApi()
 * ```
 */
interface FlavorPreferences {
    var flavor: ProductFlavor.Flavor
    val observableFlavor: LiveData<ProductFlavor.Flavor>
}

@Singleton
class FlavorPreferencesImpl @Inject constructor(@ApplicationContext context: Context) : FlavorPreferences {
    companion object {
        private const val NAME = "${BuildConfig.APPLICATION_ID}.flavor"

        object Keys {
            const val FLAVOR = "FLAVOR"
        }
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            NAME, Context.MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
        }
    }

    override var flavor: ProductFlavor.Flavor by FlavorPreference(prefs, Keys.FLAVOR)

    private val _observableFlavor = MutableLiveData<ProductFlavor.Flavor>(ProductFlavor.CURRENT)
    override val observableFlavor: LiveData<ProductFlavor.Flavor>
        get() {
            _observableFlavor.value = flavor
            return _observableFlavor
        }

    private val onSharedPreferenceChangeListener = OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            Keys.FLAVOR -> _observableFlavor.value = flavor
        }
    }

    class FlavorPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: ProductFlavor.Flavor = ProductFlavor.CURRENT
    ) : ReadWriteProperty<Any, ProductFlavor.Flavor> {

        @WorkerThread
        override fun getValue(thisRef: Any, property: KProperty<*>): ProductFlavor.Flavor {
            return preferences.value.getString(name, defaultValue.name)?.toProductFlavor() ?: defaultValue
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: ProductFlavor.Flavor) {
            preferences.value.edit { putString(name, value.name) }
        }

        private fun String.toProductFlavor(): ProductFlavor.Flavor = when (this) {
            ProductFlavor.Flavor.DEV.name -> ProductFlavor.Flavor.DEV
            ProductFlavor.Flavor.QA.name -> ProductFlavor.Flavor.QA
            ProductFlavor.Flavor.PRODUCTION.name -> ProductFlavor.Flavor.PRODUCTION
            else -> ProductFlavor.CURRENT
        }
    }
}
