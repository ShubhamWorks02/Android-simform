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
package com.example.androidwebservice.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.androidwebservice.BR

/**
 * Base fragment for all fragments
 */
abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    protected lateinit var binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupViewModel()
        initialize()
    }

    /**
     * Get the layout resource ID for the fragment
     */
    @LayoutRes
    abstract fun getLayoutResId(): Int

    /**
     * Setup [ViewModel]
     */
    open fun setupViewModel() {}

    /**
     * Called when onCreate() is executed.
     * This method will be called after setupUi() and setupViewModel() has been executed
     * @see setupUi
     * @see setupViewModel
     */
    open fun initialize() {}

    /**
     * Setup the UI
     */
    private fun setupUi() = with(binding) {
        lifecycleOwner = this@BaseFragment
        setVariable(BR.viewModel, viewModel)
        executePendingBindings()
    }
}
