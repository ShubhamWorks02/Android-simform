package com.example.androidkt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.androidkt.databinding.FragmentHomeBinding
import com.example.androidkt.utils.observeEvent

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: MainViewModel by activityViewModels()
    // private val viewModel: HomeViewModel by viewModels()
    private val adapter: WalletAdapter by lazy {
        WalletAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvWalletList.adapter = adapter
        sharedViewModel.wallet.observeEvent(viewLifecycleOwner) {wallet ->
            sharedViewModel.addWallet(wallet)
        }

        sharedViewModel.walletListLive.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}