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
package com.example.androidwebservice.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.androidwebservice.utils.result.Event
import com.example.androidwebservice.utils.result.EventObserver

/**
 * Extension function for observing [LiveData] containing [Event]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 */
fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, EventObserver {
        func(it)
    })
