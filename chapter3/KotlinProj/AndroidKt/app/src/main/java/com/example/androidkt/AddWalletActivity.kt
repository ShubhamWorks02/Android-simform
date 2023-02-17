package com.example.androidkt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.androidkt.AddWalletViewModel.CardSkin
import com.example.androidkt.databinding.ActivityWalletAddBinding
import com.example.androidkt.utils.ExpiryDateTextWatcher


class AddWalletActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWalletAddBinding
    private val addWalletViewModel: AddWalletViewModel by viewModels()
    private var selectedSkinColor: CardSkin = CardSkin.BLUE
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityWalletAddBinding?>(
                this,
                R.layout.activity_wallet_add
            ).apply {
                viewModel = addWalletViewModel
                tvExpireDate.addTextChangedListener(ExpiryDateTextWatcher())
                imgBackArrow.setOnClickListener {
                    backPressedCallback.handleOnBackPressed()
                }
                tvCardName.doOnTextChanged { text, _, _, _ ->
                    val containsNonAlphabetic = text?.any { !it.isLetter() } ?: false
                    if (containsNonAlphabetic) {
                        binding.tvCardName.setText(text?.filter { it.isLetter() })
                        binding.tvCardName.setSelection(binding.tvCardName.text?.length ?: 0)
                    } else {
                        binding.tvCardName.error = null
                    }
                }
                root.setOnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus) {
                        v.hideKeyboard()
                    }
                }
            }
        binding.lifecycleOwner = this
        addWalletViewModel.message.observe(this) { message ->
            Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
        }
        onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }


    fun changeSkin(view: View) {
        selectedSkinColor = when (view.id) {
            binding.btnSkinTrailingFirst.id -> {
                CardSkin.YELLOW
            }

            binding.btnSkinTrailingSecond.id -> {
                CardSkin.GREEN
            }

            binding.btnSkinTrailingThird.id -> {
                CardSkin.BLUE
            }

            else -> {
                CardSkin.BLUE
            }
        }
        addWalletViewModel.selectedSkinColor.value = selectedSkinColor
    }

    fun saveCard(view: View) {
        val cardName = addWalletViewModel.cardName.value
        val cardNumber = addWalletViewModel.cardNumber.value
        val expireDate = addWalletViewModel.expireDate.value
        val cvv = addWalletViewModel.cvv.value

        if (!cardName.isNullOrEmpty() && cardName.length >= 2 &&
            !cardNumber.isNullOrEmpty() && cardNumber.length == 16 &&
            !expireDate.isNullOrEmpty() && cvv != null && cvv.length == 3
        ) {
            val intent = Intent()
            intent.putExtra("cardName", cardName)
            intent.putExtra("cardNumber", cardNumber)
            intent.putExtra("expireDate", expireDate)
            intent.putExtra("cvv", cvv)
            intent.putExtra("skin", selectedSkinColor)
            intent.putExtra("cardBalance", addWalletViewModel.cardBalance.value)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(this, "Please enter valid card details", Toast.LENGTH_SHORT).show()
        }
    }
}