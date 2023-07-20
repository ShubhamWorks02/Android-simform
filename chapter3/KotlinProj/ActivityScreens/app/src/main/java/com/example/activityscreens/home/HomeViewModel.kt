package com.example.activityscreens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val _headingName = MutableLiveData<String>()
    val headingName: LiveData<String> = _headingName

    fun updateHeadingName(newHeadingName: String) {
        _headingName.value = newHeadingName
    }
}
