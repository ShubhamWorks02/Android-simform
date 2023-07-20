package com.example.activityscreens.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activityscreens.utils.ImageData

class HomeViewModel : ViewModel() {
    val _headingName = MutableLiveData<String>()
    val headingName: LiveData<String> = _headingName
    val trailingTopIconId = MutableLiveData<Int?>(null)

    fun updateHeadingName(newHeadingName: String) {
        _headingName.value = newHeadingName
    }

    fun updateTrailingTopIcon(id: Int?) {
        trailingTopIconId.value = if (id == null) {
            null
        } else {
            ImageData.imageList[id].id
        }
    }
}
