package com.example.activityscreens.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activityscreens.data.remote.request.UserRequest
import com.example.activityscreens.data.repository.UserRepository
import com.example.activityscreens.utils.onError
import com.example.activityscreens.utils.onException
import com.example.activityscreens.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val message = MutableLiveData<String>()
    val isValidated = MutableLiveData<Boolean>()
    val isTokenArrived = MutableLiveData<Boolean>()

    fun login() {
        val emailValue = email.value
        if (!emailValue.isNullOrEmpty() && !password.value.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(
                emailValue.toString()
            ).matches()
        ) {
            viewModelScope.launch {
                val response = repository.validateUser(
                    UserRequest(
                        email.value.toString(),
                        password.value.toString()
                    )
                )
                response.apply {
                    onSuccess {
                        isValidated.value = true
                        isTokenArrived.value = true
                    }
                    onError { code, message ->
                        Log.d("Code", "$code: Error occurred with description ($message)")
                        isValidated.value = false
                    }
                    onException {
                        isValidated.value = false
                    }
                }
            }
            return
        }
        message.postValue("Please Fill above details")
    }
}
