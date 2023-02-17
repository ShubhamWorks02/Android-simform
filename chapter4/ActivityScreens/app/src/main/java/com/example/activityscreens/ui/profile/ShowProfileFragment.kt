package com.example.activityscreens.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.activityscreens.data.remote.response.UserDetails
import com.example.activityscreens.databinding.FragmentShowProfileBinding

class ShowProfileFragment : Fragment() {
    private lateinit var binding: FragmentShowProfileBinding
    private val args: ShowProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowProfileBinding.inflate(inflater, container, false)
        val userDetails: UserDetails = args.userDetails
        binding.data = userDetails
        return binding.root
    }
}
