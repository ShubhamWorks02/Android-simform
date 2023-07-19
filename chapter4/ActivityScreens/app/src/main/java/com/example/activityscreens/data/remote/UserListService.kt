package com.example.activityscreens.data.remote

import com.example.activityscreens.data.remote.apiresult.ApiResult
import com.example.activityscreens.data.remote.request.UserRequest
import com.example.activityscreens.data.remote.response.User
import com.example.activityscreens.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserListService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResult<User>

    @POST("api/register")
    suspend fun validateUser(@Body userCredential: UserRequest): ApiResult<UserResponse>

    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}
