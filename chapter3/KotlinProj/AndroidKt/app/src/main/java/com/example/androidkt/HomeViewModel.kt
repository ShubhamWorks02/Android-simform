package com.example.androidkt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel()  {

    private val walletList = mutableListOf<Wallet>()

    private val _walletListLive = MutableLiveData<List<Wallet>>(emptyList())
    val walletListLive:  LiveData<List<Wallet>> = _walletListLive

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