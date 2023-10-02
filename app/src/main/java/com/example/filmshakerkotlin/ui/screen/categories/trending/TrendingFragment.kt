package com.example.filmshakerkotlin.ui.screen.categories.trending

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.databinding.FragmentTrendingBinding
import com.example.filmshakerkotlin.domain.model.trending.TrendingDetail
import com.example.filmshakerkotlin.ui.adapter.TrendingMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendingFragment : BaseFragment<TrendingViewModel, FragmentTrendingBinding>(
    TrendingViewModel::class.java,
    FragmentTrendingBinding::inflate
) {

    private var trendingMovieAdapter: TrendingMovieAdapter? = null
    private var trendingList: ArrayList<TrendingDetail> = arrayListOf()
    private lateinit var fragmentManager: FragmentManager

    override fun onCreateViewInvoke() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest(::handleViewState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrendingMovies()
        fragmentManager = requireActivity().supportFragmentManager
    }

    private fun handleViewState(trendingState: TrendingState) {
        trendingState.movieList.let {
            trendingList = trendingState.movieList!!
            if (trendingState.isLoading == false) {
                if (trendingList.isEmpty()) {
                    Toast.makeText(requireContext(), "Movie list is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    trendingMovieAdapter = TrendingMovieAdapter(trendingList, fragmentManager)
                    binding.recyclerView.adapter = trendingMovieAdapter


                }
            }
        }
    }

}