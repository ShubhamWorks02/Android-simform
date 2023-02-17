/*
 * Copyright (C) 2021 The Android Open Source Project.
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

package com.example.android.marsphotos.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.data.remote.MarsPhoto
import com.example.android.marsphotos.data.repository.PhotoRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

@HiltViewModel
class OverviewViewModel @Inject constructor(private val repository: PhotoRepository) : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _marsPhotos = MutableLiveData<List<MarsPhoto>>()
    val marsPhotos: LiveData<List<MarsPhoto>> = _marsPhotos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                _marsPhotos.value = repository.getPhotos()
                Log.d("Count:", _marsPhotos.value!!.size.toString())
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}
