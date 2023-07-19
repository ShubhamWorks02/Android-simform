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
package com.example.androidwebservice.ui.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidwebservice.data.remote.response.User
import com.example.androidwebservice.data.repository.UserRepository
import com.example.androidwebservice.di.IoDispatcher
import com.example.androidwebservice.di.MainDispatcher
import com.example.androidwebservice.ui.base.BaseViewModel
import com.example.androidwebservice.utils.extension.onError
import com.example.androidwebservice.utils.extension.onException
import com.example.androidwebservice.utils.extension.onSuccess
import com.example.androidwebservice.utils.result.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for [com.example.androidwebservice.ui.sample.SampleActivity]
 */
@HiltViewModel
class SampleViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _onNewUserList = MutableLiveData<Event<List<User>>>()
    val onNewUserList: LiveData<Event<List<User>>>
        get() = _onNewUserList

    private val _isLoadingPage = MutableLiveData<Boolean>()
    val isLoadingPage: LiveData<Boolean>
        get() = _isLoadingPage

    private val _showShimmer = MutableLiveData<Boolean>()
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    private var currentPage = 0

    init {
        loadMoreUsers()
    }

    fun loadMoreUsers() {
        _isLoadingPage.value = true
        currentPage += 1

        if (currentPage == 1) {
            _showShimmer.value = true
        }

        viewModelScope.launch(ioDispatcher) {
            userRepository
                .loadUsers(currentPage)
                .onSuccess {
                    withContext(mainDispatcher) {
                        _isLoadingPage.value = false
                        _onNewUserList.value = Event(it.results)
                        if (currentPage == 1) {
                            _showShimmer.value = false
                        }
                    }
                }.onError { code, message ->
                    Timber.e(code.toString() + message)
                    withContext(mainDispatcher) { handleApiFailure() }
                }.onException {
                    Timber.e(it.message)
                    withContext(mainDispatcher) { handleApiFailure() }
                }
        }
    }

    /**
     * Handle the failure occuring in API call
     */
    private fun handleApiFailure() {
        _isLoadingPage.value = false
        if (currentPage == 1) {
            _showShimmer.value = false
        }
        currentPage -= 1 // Revert increased count
    }
}
