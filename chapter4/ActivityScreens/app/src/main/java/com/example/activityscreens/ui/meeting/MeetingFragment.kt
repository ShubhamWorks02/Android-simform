package com.example.activityscreens.ui.meeting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activityscreens.R

class MeetingFragment : Fragment(R.layout.fragment_meeting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WelcomeFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("WelcomeFragment", "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("WelcomeFragment", "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("WelcomeFragment", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("WelcomeFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("WelcomeFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("WelcomeFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("WelcomeFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("WelcomeFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("WelcomeFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("WelcomeFragment", "onDetach")
    }
}

