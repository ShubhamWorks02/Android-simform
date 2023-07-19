package com.example.activityscreens.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Shubham", "Bhatt")
                .addHeader("appid", "hello")
                .addHeader("deviceplatform", "android")
                .removeHeader("Content-Type")
                .addHeader("Content-Type", "Shubham/json")
                .build()

        )
    }.apply {
        Log.d("response", this.toString())
    }

}