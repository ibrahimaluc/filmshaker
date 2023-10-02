package com.example.filmshakerkotlin.ui.screen.categories.soon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.databinding.FragmentSoonBinding
import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.example.filmshakerkotlin.ui.adapter.SoonMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SoonFragment : BaseFragment<SoonViewModel, FragmentSoonBinding>(
    SoonViewModel::class.java,
    FragmentSoonBinding::inflate
) {

    private var soonMovieAdapter: SoonMovieAdapter? = null
    private var soonList: ArrayList<MovieDetail> = arrayListOf()
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
        viewModel.getSoonMovies()
        fragmentManager = requireActivity().supportFragmentManager
    }

    private fun handleViewState(soonState: SoonState) {
        soonState.movieList.let {
            soonList = soonState.movieList!!
            if (soonState.isLoading == false) {
                if (soonList.isEmpty()) {
                    Toast.makeText(requireContext(), "Movie list is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    soonMovieAdapter = SoonMovieAdapter(soonList, fragmentManager)
                    binding.recyclerView.adapter = soonMovieAdapter


                }
            }
        }
    }

}