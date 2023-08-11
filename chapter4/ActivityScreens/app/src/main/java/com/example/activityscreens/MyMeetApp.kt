package com.example.activityscreens

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.activityscreens.utils.NetworkUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyMeetApp : Application() {

    private val validNetworks: MutableList<Network> = mutableListOf()

    override fun onCreate() {
        super.onCreate()
        observeNetwork()
    }

    private fun observeNetwork() {
        val connectivityManager: ConnectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                    val hasInternet =
                        networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                            ?: false
                    if (hasInternet) {
                        validNetworks.add(network)
                        checkValidNetworks()
                    }
                }

                override fun onLost(network: Network) {
                    validNetworks.remove(network)
                    checkValidNetworks()
                }
            }
        )
    }

    private fun checkValidNetworks() {
        NetworkUtil.isNetworkConnected = validNetworks.isNotEmpty()
    }
}