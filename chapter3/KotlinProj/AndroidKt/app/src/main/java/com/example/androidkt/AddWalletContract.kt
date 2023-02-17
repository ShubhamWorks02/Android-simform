package com.example.androidkt

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.androidkt.AddWalletViewModel.CardSkin

class AddWalletContract : ActivityResultContract<Unit, Wallet?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context,AddWalletActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Wallet? {
        return if (intent != null) {
          intent.extras?.run {
                Wallet(
                    getString("cardName", "Empty"),
                    getString("cardNumber", "Empty"),
                    getString("expireDate", "Empty"),
                    getString("cvv", "123"),
                    CardSkin.valueOf(getString("skin", "GREEN")),
                    getInt("cardBalance",(1000..100000).random())
                )
            } //Wallet("DefaultCard","1234567890123456","1234","123", CardSkin.YELLOW,0)
        }// else Wallet("DefaultCard","1234567890123456","1234","123", CardSkin.YELLOW,0)
        else return null
    }


}