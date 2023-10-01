package com.example.filmshakerkotlin.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmshakerkotlin.ui.screen.categories.nowplaying.NowPlayingFragment
import com.example.filmshakerkotlin.ui.screen.categories.soon.SoonFragment
import com.example.filmshakerkotlin.ui.screen.categories.trending.TrendingFragment

class TabPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SoonFragment()
            1 -> NowPlayingFragment()
            2 -> TrendingFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}
