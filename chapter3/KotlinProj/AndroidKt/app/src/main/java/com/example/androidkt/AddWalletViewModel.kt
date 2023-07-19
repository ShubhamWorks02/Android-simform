package com.example.androidkt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddWalletViewModel: ViewModel() {
    enum class CardSkin {
        BLUE,
        GREEN,
        YELLOW
    }

    val cardName = MutableLiveData<String>()
    val cardNumber = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val cvv = MutableLiveData<String>()
    val selectedSkinColor = MutableLiveData<CardSkin?>(CardSkin.BLUE)
    val cardHolderName = MutableLiveData<String>("David Kowalski")
    val cardBalance = MutableLiveData<Int>()
    val message = MutableLiveData<String>()

}

