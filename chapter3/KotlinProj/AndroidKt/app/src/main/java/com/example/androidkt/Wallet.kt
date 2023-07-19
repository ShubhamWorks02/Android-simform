package com.example.androidkt

data class Wallet(
    val cardName: String,
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val cardSkin: AddWalletViewModel.CardSkin,
    val cardBalance: Int
)

object WalletData {
    val wallets = mutableListOf(
        Wallet(
            "MasterCard",
            "1124 4444 1124 4444",
            "12/06",
            "568",
            AddWalletViewModel.CardSkin.BLUE,
            (1000..100000).random()
        ),
        Wallet(
            "MasterCard",
            "1124 4444 1124 4444",
            "12/06",
            "568",
            AddWalletViewModel.CardSkin.GREEN,
            (1000..100000).random()
        ),
        Wallet(
            "MasterCard",
            "1124 4444 1124 4444",
            "12/06",
            "568",
            AddWalletViewModel.CardSkin.YELLOW,
            (1000..100000).random()
        )
    )
}

