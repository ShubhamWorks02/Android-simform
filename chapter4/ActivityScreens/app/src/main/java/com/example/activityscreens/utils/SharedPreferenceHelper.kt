package com.example.activityscreens.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("login", 0)
        editor = sharedPreferences.edit()
    }

    fun putUserState(key: String, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

    fun getUserState(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }
}