/**
 * Copyright COPYRIGHT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androidwebservice.ui.splash

import androidx.activity.viewModels
import com.example.androidwebservice.R
import com.example.androidwebservice.ui.base.BaseAppCompatActivity
import com.example.androidwebservice.databinding.ActivitySplashBinding
import com.example.androidwebservice.ui.sample.SampleActivity
import com.example.androidwebservice.utils.extension.launchActivity
import com.example.androidwebservice.utils.extension.observeEvent
import com.example.androidwebservice.ui.splash.SplashViewModel.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
SplashActivity : BaseAppCompatActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun setupViewModel() {
        super.setupViewModel()

        viewModel.goToScreen.observeEvent(this) { destination ->
            when (destination) {
                Destination.Home -> openHomeScreen()
                Destination.Login -> openLoginScreen()
                Destination.Sample -> openSampleScreen()
            }
        }
    }

    private fun openLoginScreen() {
        // TODO : Open Login screen
    }

    private fun openHomeScreen() {
        // TODO : Open Home screen
    }

    private fun openSampleScreen() {
        launchActivity<SampleActivity>()
    }
}
