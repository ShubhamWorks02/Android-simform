package com.example.activityscreens.utils

import android.util.Log
import kotlin.properties.Delegates

object NetworkUtil {
    var isNetworkConnected by Delegates.observable(false) { _, _, newValue ->
        Log.d("Network Status", "Network status ${if (newValue) "CONNECTED" else "DISCONNECTED"}")
    }
}
