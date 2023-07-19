package com.example.activityscreens.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardHelper {

    @SuppressLint("ClickableViewAccessibility")
    fun setupKeyboardHiding(view: View, activity: Activity) {
        view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                hideSoftKeyboard(view, activity)
            }
            false
        }
    }

    private fun hideSoftKeyboard(view: View, activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = activity.currentFocus
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
            currentFocus.clearFocus()
        }
    }
}
