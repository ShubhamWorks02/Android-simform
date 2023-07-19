package com.example.activityscreens.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.activityscreens.R
import com.example.activityscreens.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val imagePickerContract = ImagePickerContract()
    private val getContent = registerForActivityResult(imagePickerContract) {
        if (it != null) {
            binding.imgProfile.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        );
        binding.lifecycleOwner = this
        binding.imgProfile.setOnClickListener {
            pickImage()
        }
        return binding.root
    }

    private fun pickImage() {
        getContent.launch(Intent().apply {
            type = "image/*"
        })
    }
}