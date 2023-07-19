package com.example.activityscreens.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activityscreens.databinding.FragmentChatChannelBinding

class ChatChannelFragment : Fragment() {
    private lateinit var binding: FragmentChatChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentChatChannelBinding.inflate(inflater).apply {
        lifecycleOwner = this@ChatChannelFragment
        binding = this
    }.root


}
