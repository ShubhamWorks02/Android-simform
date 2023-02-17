package com.example.androidkt.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.androidkt.AddWalletViewModel
import com.example.androidkt.R
import androidx.core.content.res.ResourcesCompat

@BindingAdapter("android:src")
fun ImageView.setImageView(@DrawableRes id: Int) {
    setImageDrawable(
        ContextCompat.getDrawable(context, id)
    )
}

@BindingAdapter("card_skin")
fun ConstraintLayout.setCardSkin(skin: AddWalletViewModel.CardSkin?) {
    background = when (skin) {
        AddWalletViewModel.CardSkin.BLUE -> ResourcesCompat.getDrawable(resources, R.drawable.gradient_card_1, null)
        AddWalletViewModel.CardSkin.GREEN -> ResourcesCompat.getDrawable(resources, R.drawable.gradient_card_2, null)
        AddWalletViewModel.CardSkin.YELLOW -> ResourcesCompat.getDrawable(resources, R.drawable.gradient_card_3, null)
        else -> ResourcesCompat.getDrawable(resources, R.drawable.gradient_card_1, null)
    }
}

@BindingAdapter("maskedValue")
fun TextView.setMaskedValue(value: String?) {
    val tempStr = "****************"
    val finalString = if (value.isNullOrEmpty()) {
        text = tempStr
        return
    } else {
        tempStr.replaceRange(value.indices,value)
    }
    text = finalString?.slice(0..3)+" "+finalString?.slice(4..7) + " **** ****"
}

