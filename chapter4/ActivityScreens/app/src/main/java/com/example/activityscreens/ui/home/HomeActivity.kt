package com.example.activityscreens.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.activityscreens.R
import com.example.activityscreens.databinding.ActivityHomeBinding
import com.example.activityscreens.utils.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferenceHelper.initialize(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        homeViewModel.updateHeadingName("Chat")

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvHome.setupWithNavController(navController)

        lifecycleScope.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                val label = it.destination.label.toString().orEmpty()
                homeViewModel.updateHeadingName(label)
                when (it.destination.label.toString()) {
                    getString(R.string.label_call) -> homeViewModel.updateTrailingTopIcon(2)
                    getString(R.string.label_profile) -> homeViewModel.updateTrailingTopIcon(1)
                    getString(R.string.label_meeting) -> homeViewModel.updateTrailingTopIcon(null)
                    getString(R.string.label_fax) -> homeViewModel.updateTrailingTopIcon(0)
                }
            }
        }

    }

}
