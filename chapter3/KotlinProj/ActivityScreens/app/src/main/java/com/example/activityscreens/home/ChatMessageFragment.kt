package com.example.activityscreens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activityscreens.databinding.FragmentChatMessageBinding

class ChatMessageFragment : Fragment() {
    private lateinit var binding: FragmentChatMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = FragmentChatMessageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // Return the root view from the binding
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
