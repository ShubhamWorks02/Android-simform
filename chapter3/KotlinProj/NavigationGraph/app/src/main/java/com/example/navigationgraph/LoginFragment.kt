package com.example.navigationgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {
    val btnLogin: Button by lazy {
        requireView().findViewById(R.id.btn_login)
    }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        btnLogin.setOnClickListener {
//            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_loginFragment_to_mainFragment) }
//        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


}