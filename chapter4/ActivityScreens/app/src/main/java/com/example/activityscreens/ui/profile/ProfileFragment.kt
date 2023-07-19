package com.example.activityscreens.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.activityscreens.R
import com.example.activityscreens.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val getContent = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogout.root.setOnClickListener {

        }
    }

    private fun pickImage() {
        getContent.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
