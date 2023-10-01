package com.example.filmshakerkotlin.ui.screen.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.core.adapter.TabPagerAdapter
import com.example.filmshakerkotlin.databinding.FragmentCategoriesBinding
import com.example.filmshakerkotlin.ui.screen.moviedetail.MovieDetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayoutMediator: TabLayoutMediator


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val tL = _binding!!.tabLayout
        viewPager = _binding!!.viewPager

        val adapter = TabPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter


        tabLayoutMediator = TabLayoutMediator(
            tL, viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Soon"
                1 -> tab.text = "Now Playing"
                2 -> tab.text = "Trending"
            }
        }
        tabLayoutMediator.attach()
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}