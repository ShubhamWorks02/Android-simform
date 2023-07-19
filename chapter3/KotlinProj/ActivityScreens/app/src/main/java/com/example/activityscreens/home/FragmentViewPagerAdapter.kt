package com.example.activityscreens.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val tabCount = 2 // Number of tabs

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChatMessageFragment()
            1 -> ChatChannelFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
