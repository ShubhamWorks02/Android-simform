package com.example.activityscreens.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.example.activityscreens.R
import com.example.activityscreens.databinding.ActivityLoginBinding
import com.example.activityscreens.ui.home.HomeActivity
import com.example.activityscreens.utils.KeyboardHelper
import com.example.activityscreens.utils.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferenceHelper.initialize(this)
        installSplashScreen()
        checkUserState()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.apply {
            message.observe(this@LoginActivity) { message ->
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
            }
            isValidated.observe(this@LoginActivity) {
                if (it) startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                else Toast.makeText(
                    this@LoginActivity,
                    "Please enter valid credentials",
                    Toast.LENGTH_SHORT
                ).show()

            }
            isTokenArrived.observe(this@LoginActivity) {
                SharedPreferenceHelper.putUserState("Logged", it)
            }
        }

        KeyboardHelper.setupKeyboardHiding(binding.scrollView, this)
    }

    private fun checkUserState() {
        val isSignedIn = SharedPreferenceHelper.getUserState("Logged", false)
        if (isSignedIn) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
