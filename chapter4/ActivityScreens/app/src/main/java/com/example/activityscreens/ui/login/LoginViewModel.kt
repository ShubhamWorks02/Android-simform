package com.example.activityscreens.ui.login

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
        if (emailValue.isNullOrEmpty()) {
            message.postValue("Your email is empty")
            return
        } else if (password.value.isNullOrEmpty()) {
            message.postValue("Your password is empty")
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(
                emailValue.toString()
            ).matches()
        ) {
            message.postValue("Email is not valid")
            return
        } else {
            viewModelScope.launch {
                val response = repository.validateUser(
                    UserRequest(
                        email.value.toString(),
                        password.value.toString()
                    )
                )
                response.apply {
                    onSuccess {
                        if (it.token.isNotEmpty()) {
                            changeValidationStatesValue(true, true)
                        } else {
                            changeValidationStatesValue(false, false)
                        }
                    }
                    onError { _, _ ->
                        changeValidationStatesValue(false, false)
                    }
                    onException {
                        changeValidationStatesValue(false, false)
                    }
                }
            }
        }
    }

    private fun changeValidationStatesValue(isValid: Boolean, tokenized: Boolean) {
        isValidated.value = isValid
        isTokenArrived.value = tokenized
    }
}
