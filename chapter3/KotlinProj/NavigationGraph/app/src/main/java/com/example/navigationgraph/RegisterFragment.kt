package com.example.navigationgraph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class RegisterFragment : Fragment() {
    private val btnGoToLogin: Button by lazy {
        requireView().findViewById(R.id.btn_go_to_login)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        btnGoToLogin.setOnClickListener {
//            Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment);
//        }
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

}