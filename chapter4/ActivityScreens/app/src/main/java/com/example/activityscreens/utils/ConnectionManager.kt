package com.example.activityscreens.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object ConnectionManager {

    private const val classTag = "ConnectionManager"

    private fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        Log.d(classTag, "hasNetworkAvailable: ${(network != null)}")
        return (network?.isConnected) ?: false
    }

    fun hasInternetConnected(context: Context): Boolean {
        if (hasNetworkAvailable(context)) {
            try {
                val connection = URL(Urls.REACHABILITY_SERVER).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1500
                connection.connect()
                Log.d(classTag, "hasInternetConnected: ${(connection.responseCode == 200)}")
                return (connection.responseCode == 200)
            } catch (e: IOException) {
                Log.e(classTag, "Error checking internet connection", e)
            }
        } else {
            Log.w(classTag, "No network available!")
        }
        Log.d(classTag, "hasInternetConnected: false")
        return false
    }

    fun hasServerConnected(context: Context): Boolean {
        if (hasNetworkAvailable(context)) {
            try {
                val connection = URL(Urls.baseUrl).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1500 // configurable
                connection.connect()
                Log.d(classTag, "hasServerConnected: ${(connection.responseCode == 200)}")
                return (connection.responseCode == 200)
            } catch (e: IOException) {
                Log.e(classTag, "Error checking internet connection", e)
            }
        } else {
            Log.w(classTag, "Server is unavailable!")
        }
        Log.d(classTag, "hasServerConnected: false")
        return false
    }
}