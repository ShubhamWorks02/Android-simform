package com.example.activityscreens.data.repository

import android.util.Log
import com.example.activityscreens.data.local.CachedUser
import com.example.activityscreens.data.local.CachedUserToUserMapper
import com.example.activityscreens.data.local.UserDao
import com.example.activityscreens.data.local.UserToCachedUserMapper
import com.example.activityscreens.data.remote.UserListService
import com.example.activityscreens.data.remote.apiresult.ApiResult
import com.example.activityscreens.data.remote.apiresult.ApiSuccess
import com.example.activityscreens.data.remote.request.UserRequest
import com.example.activityscreens.data.remote.response.Support
import com.example.activityscreens.data.remote.response.User
import com.example.activityscreens.data.remote.response.UserResponse
import com.example.activityscreens.utils.SharedPreferenceHelper
import com.example.activityscreens.utils.Urls
import com.example.activityscreens.utils.onError
import com.example.activityscreens.utils.onException
import com.example.activityscreens.utils.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    suspend fun getUsers(page: Int = 1): ApiResult<User>

    suspend fun validateUser(userRequest: UserRequest): ApiResult<UserResponse>

    suspend fun deleteUser(id: Int): Response<Unit>

    var isApiCallFinished: Boolean
}

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: UserListService,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepository {
    private val perPage = 4
    override var isApiCallFinished: Boolean = SharedPreferenceHelper.getUserState("Call", false)
    var cachedUsersTotal: MutableList<CachedUser> = mutableListOf()

    override suspend fun getUsers(page: Int): ApiResult<User> {
        val response = apiService.getUserList(page, perPage)
        val result = response.apply {
            onSuccess {
                val cachedUsersFromPage = it.data.map { UserToCachedUserMapper.map(it) } // edited
                withContext(ioDispatcher) {
                    userDao.insertUsers(cachedUsersFromPage)
                }
            }
            onError { code, message ->
                Log.d("error", "Failed to fetch data with error code: $code and reason: $message")
            }
            onException {
                Log.d("error", "exception $it")
            }
        }

        withContext(ioDispatcher) {
            if (isApiCallFinished) {
                cachedUsersTotal = userDao.getAllUsers() as MutableList<CachedUser>
                Log.d("user", cachedUsersTotal.size.toString())
            }
        }

        if (cachedUsersTotal.isNotEmpty() && isApiCallFinished) {
            val users = cachedUsersTotal.map { CachedUserToUserMapper.map(it) }
            val support = Support(
                Urls.supportUrl,
                Urls.supportText
            )
            return ApiSuccess(User(page, perPage, 12, 1, users, support))
        }
        return result

    }

    override suspend fun validateUser(userRequest: UserRequest): ApiResult<UserResponse> =
        apiService.validateUser(userRequest)

    override suspend fun deleteUser(id: Int): Response<Unit> = apiService.deleteUser(id)
}
