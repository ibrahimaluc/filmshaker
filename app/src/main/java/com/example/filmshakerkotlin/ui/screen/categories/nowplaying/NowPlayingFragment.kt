package com.example.filmshakerkotlin.ui.screen.categories.nowplaying

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.databinding.FragmentNowPlayingBinding
import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.example.filmshakerkotlin.ui.adapter.NowPlayingMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<NowPlayingViewModel, FragmentNowPlayingBinding>(
    NowPlayingViewModel::class.java,
    FragmentNowPlayingBinding::inflate
) {

    private var nowPlayingMovieAdapter: NowPlayingMovieAdapter? = null
    private var nowPlayingList: ArrayList<MovieDetail> = arrayListOf()
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
        viewModel.getNowPlayingMovies()
        fragmentManager = requireActivity().supportFragmentManager
    }

    private fun handleViewState(nowPlayingState: NowPlayingState) {
        nowPlayingState.movieList.let {
            nowPlayingList = nowPlayingState.movieList!!
            if (nowPlayingState.isLoading == false) {
                if (nowPlayingList.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Movie list is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    nowPlayingMovieAdapter = NowPlayingMovieAdapter(nowPlayingList, fragmentManager)
                    binding.recyclerView.adapter = nowPlayingMovieAdapter
                }
            }
        }
    }

}