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
package com.example.androidwebservice.data.repository

import com.example.androidwebservice.data.remote.ApiService
import com.example.androidwebservice.data.remote.apiresult.ApiResult
import com.example.androidwebservice.data.remote.response.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    /**
     * Loads [List] of [UserResponse]
     */
    suspend fun loadUsers(page: Int = 1): ApiResult<UserResponse>
}

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun loadUsers(page: Int): ApiResult<UserResponse> =
        apiService.loadUsers(page)
}
