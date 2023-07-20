package com.example.activityscreens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val message = MutableLiveData<String>()

    fun login() {
        val emailValue = email.value
        if (!emailValue.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            message.postValue("Welcome!")
            return
        }
        message.postValue("Invalid Credentials")
    }
}
