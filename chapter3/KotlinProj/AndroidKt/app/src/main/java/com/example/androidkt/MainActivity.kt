package com.example.androidkt

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.androidkt.databinding.ActivityMainBinding
import com.example.androidkt.utils.Event

interface BottomNavigationVisibilityListener {
    fun setBottomNavigationVisibility(isVisible: Boolean)
}

class MainActivity : AppCompatActivity(), BottomNavigationVisibilityListener {
    private lateinit var binding: ActivityMainBinding
    private val addWalletContract = AddWalletContract()
    private val viewModel: MainViewModel by viewModels()
    private val getContent = registerForActivityResult(addWalletContract) {
        it?.let {
            viewModel.wallet.value = Event(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                bnvHomeWallet.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.menu_wallet -> {
                            true
                        }

                        R.id.menu_chart -> {
                            supportFragmentManager.commit {
                                replace(
                                    binding.fragmentContainerView.id,
                                    TransactionFragment::class.java,
                                    null
                                )
                                addToBackStack(null)
                            }
                            true
                        }

                        else -> false
                    }
                }

                fabAddWallet.setOnClickListener {
                    getContent.launch(Unit)
                }

            }
        binding.lifecycleOwner = this
        supportFragmentManager.commit {
            replace(binding.fragmentContainerView.id, HomeFragment::class.java, null)
        }

    }

    override fun setBottomNavigationVisibility(isVisible: Boolean) = with(binding) {
        bnvHomeWallet.selectedItemId = R.id.menu_wallet
        if (!isVisible) {
            fabAddWallet.visibility = View.GONE
            bnvHomeWallet.visibility = View.GONE
            return
        }
        fabAddWallet.visibility = View.VISIBLE
        bnvHomeWallet.visibility = View.VISIBLE
    }

}