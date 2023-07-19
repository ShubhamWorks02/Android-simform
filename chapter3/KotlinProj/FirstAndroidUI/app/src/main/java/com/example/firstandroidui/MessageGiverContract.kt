package com.example.firstandroidui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MessageGiverContract: ActivityResultContract<String, Intent?>()  {
    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context,WelcomeActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? {
        return intent
    }

}