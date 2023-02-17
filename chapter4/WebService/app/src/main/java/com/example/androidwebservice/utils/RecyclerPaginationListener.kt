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
package com.example.androidwebservice.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwebservice.ui.base.BaseRecyclerAdapter

/**
 * Recycler pagination listener invoked [onPageChange] lambda when [itemRemainCount] is reached.
 *
 * @param itemRemainCount Remaining visible item before [onPageChange] invocation
 * @param onPageChange Invoked when page change required
 */
class RecyclerPaginationListener(private val itemRemainCount: Int = 1, private val onPageChange: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val adapter = recyclerView.adapter as? BaseRecyclerAdapter<*>
        adapter?.let {
            if (!adapter.isLoading()) {
                val linearLayoutManager: LinearLayoutManager? = recyclerView.layoutManager as? LinearLayoutManager
                linearLayoutManager?.let {
                    if (it.findLastCompletelyVisibleItemPosition() >= it.itemCount - itemRemainCount) {
                        onPageChange.invoke()
                    }
                }
            }
        }
    }
}
