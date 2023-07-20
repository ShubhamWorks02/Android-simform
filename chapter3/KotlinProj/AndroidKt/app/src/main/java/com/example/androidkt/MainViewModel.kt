package com.example.androidkt

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkt.utils.Event

class MainViewModel: ViewModel()  {
    val wallet = MutableLiveData<Event<Wallet>>()

    private val walletList = mutableListOf<Wallet>()

    private val _walletListLive = MutableLiveData<List<Wallet>>(emptyList())
    val walletListLive: LiveData<List<Wallet>> = _walletListLive

    init {
        hashCode()
    }
    init {
        walletList.addAll(WalletData.wallets)
        updateWallets()
    }

    fun addWallet(wallet: Wallet) {
        walletList.add(wallet)
        updateWallets()
    }

    private fun updateWallets() {
        _walletListLive.value = walletList
    }
}