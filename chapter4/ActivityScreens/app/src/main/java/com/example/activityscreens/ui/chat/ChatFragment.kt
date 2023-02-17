package com.example.activityscreens.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.activityscreens.R
import com.google.android.material.tabs.TabLayout

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var chatTabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: FragmentViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatTabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager_holder)
        viewPagerAdapter = FragmentViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 0

        chatTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                chatTabLayout.selectTab(chatTabLayout.getTabAt(position))
            }
        })

    }

}
