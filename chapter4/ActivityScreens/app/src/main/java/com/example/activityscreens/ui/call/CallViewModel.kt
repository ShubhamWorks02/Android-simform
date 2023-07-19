package com.example.activityscreens.ui.call

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activityscreens.data.remote.response.UserDetails
import com.example.activityscreens.data.repository.UserRepository
import com.example.activityscreens.utils.SharedPreferenceHelper
import com.example.activityscreens.utils.onError
import com.example.activityscreens.utils.onException
import com.example.activityscreens.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userDetail = MutableLiveData<List<UserDetails>>()
    val userDetail: LiveData<List<UserDetails>> = _userDetail
    val currentPage = MutableLiveData<Int>(0)
    val totalPage = MutableLiveData<Int>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isUserDeleted = MutableLiveData<Boolean>()
    var isSearching = false

    val isUserDeleted: LiveData<Boolean>
        get() = _isUserDeleted

    init {
        getUsers()
    }

    fun getUsers() {
        if (_isLoading.value == true) return
        _isLoading.value = true
        currentPage.value = currentPage.value?.plus(1)
        viewModelScope.launch {
            if (!repository.isApiCallFinished)
                delay(2000)
            val user = currentPage.value?.let { repository.getUsers(it) }
            user?.apply {
                onSuccess {
                    withContext(Dispatchers.Main) {
                        totalPage.value = it.totalPages
                        val newList = _userDetail.value.orEmpty().toMutableList()
                        newList.addAll(it.data)
                        _userDetail.value = newList
                    }
                    if (it.page == it.totalPages) {
                        repository.isApiCallFinished = true
                        SharedPreferenceHelper.putUserState("Call", true)
                        Log.d("page", "current : ${it.page} total: ${it.totalPages}")
                    }
                }
                onError { code, _ ->
                    Log.d("status Code", code.toString())
                    currentPage.value = currentPage.value?.minus(1)
                }
                onException {
                    currentPage.value = currentPage.value?.minus(1)
                }
                _isLoading.value = false
            }
        }
    }

    fun deleteUser(user: UserDetails) {
        viewModelScope.launch {
            val response = repository.deleteUser(user.id)
            response.apply {
                Log.d("delete", this.toString())
                if (this.code() == 204) {
                    _userDetail.value = userDetail.value?.minus(user)
                    _isUserDeleted.postValue(true)
                } else _isUserDeleted.postValue(false)
            }
        }
    }

}
