package com.example.activityscreens.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class ImagePickerContract : ActivityResultContract<Intent, Uri?>() {

    override fun createIntent(context: Context, input: Intent): Intent {
        return Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            .apply {
                type = input.type
            }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            return intent.data
        }
        return null
    }
}