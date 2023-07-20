package com.example.activityscreens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.activityscreens.R
import com.example.activityscreens.databinding.FragmentCallBinding

class CallFragment : Fragment(R.layout.fragment_call) {

    private lateinit var binding: FragmentCallBinding
    private val viewModel: CallViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val itemDecoration = VerticalSpaceItemDecoration(0)
        binding.rvContactList.addItemDecoration(itemDecoration)
        if (container != null) {
            binding.rvContactList.adapter = ContactAdapter(Data.contacts)
        }
        // Return the root view from the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}