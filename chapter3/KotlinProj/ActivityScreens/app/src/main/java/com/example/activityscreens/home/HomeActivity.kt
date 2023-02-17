package com.example.activityscreens.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.activityscreens.R
import com.example.activityscreens.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            }
        }
    }

    override fun onResume() {
        Log.d("backStackEntryCount", supportFragmentManager.backStackEntryCount.toString())
        supportFragmentManager.backStackEntryCount
        super.onResume()
    }

}
