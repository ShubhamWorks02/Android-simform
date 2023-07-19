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
package com.example.activityscreens.utils

import com.example.activityscreens.data.remote.apiresult.ApiError
import com.example.activityscreens.data.remote.apiresult.ApiException
import com.example.activityscreens.data.remote.apiresult.ApiResult
import com.example.activityscreens.data.remote.apiresult.ApiSuccess


/**
 * Execute [executable] if the [ApiResult] is of type [ApiSuccess]
 *
 * @param executable    Block to execute on [ApiSuccess]
 *
 * @return [ApiResult] instance
 */
suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiSuccess<T>) {
        executable(data)
    }
}

/**
 * Execute [executable] if the [ApiResult] is of type [ApiError]
 *
 * @param executable    Block to execute on [ApiError]
 *
 * @return [ApiResult] instance
 */
suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ApiResult<T> = apply {
    if (this is ApiError<T>) {
        executable(code, message)
    }
}

/**
 * Execute [executable] if the [ApiResult] is of type [ApiException]
 *
 * @param executable    Block to execute on [ApiException]
 *
 * @return [ApiResult] instance
 */
suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ApiResult<T> = apply {
    if (this is ApiException<T>) {
        executable(exception)
    }
}

/**
 * Returns [ApiResult] containing result of [transform] applied to [ApiSuccess].
 *
 * Use this extension when you want to transform a value to another value on [ApiSuccess].
 *
 * @param transform     Apply transformation to [ApiSuccess]
 *
 * @return [ApiResult] instance
 */
suspend fun <T : Any, R : Any> ApiResult<T>.mapOnSuccess(
    transform: suspend (T) -> R
): ApiResult<R> = when (this) {
    is ApiError -> ApiError(code, message)
    is ApiException -> ApiException(exception)
    is ApiSuccess -> ApiSuccess(transform(data))
}
