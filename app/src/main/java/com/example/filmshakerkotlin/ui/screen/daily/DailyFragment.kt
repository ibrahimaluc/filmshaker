package com.example.filmshakerkotlin.ui.screen.daily

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.data.local.later.LaterDatabase
import com.example.filmshakerkotlin.data.local.later.LaterEntity
import com.example.filmshakerkotlin.data.local.watched.WatchedDatabase
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity
import com.example.filmshakerkotlin.databinding.FragmentDailyBinding
import com.example.filmshakerkotlin.ui.screen.moviedetail.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class DailyFragment : BaseFragment<DailyViewModel, FragmentDailyBinding>(
    DailyViewModel::class.java,
    FragmentDailyBinding::inflate
) {
    override fun onCreateViewInvoke() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest(::handleViewState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSharedPreferences(
            requireContext().applicationContext.getSharedPreferences(
                "my_prefs",
                Context.MODE_PRIVATE
            )
        )
        viewModel.isSameDay()
        posterButtonClick()
        addLater()
        addWatched()

    }

    private fun posterButtonClick() {
        binding.afisImageButton.setOnClickListener {
            val bundle = Bundle()
            val selectedMovie = binding.movieDetail?.id ?: return@setOnClickListener
            bundle.putInt("selectedMovie", selectedMovie)
            findNavController().navigate(R.id.action_dailyFragment_to_movieDetailFragment, bundle)
        }
    }


    private fun addLater() {
        binding.laterButton.setOnClickListener {
            val laterEntity = LaterEntity(
                id = binding.movieDetail?.id,
                overview = binding.movieDetail?.overview,
                posterPath = binding.movieDetail?.posterPath,
                title = binding.movieDetail?.title,
                voteAverage = binding.movieDetail?.voteAverage
            )
            val laterDao = LaterDatabase.getInstance(requireContext()).laterDao()
            lifecycleScope.launch {
                val existingEntity = laterDao.getLaterEntityById(laterEntity.id)
                if (existingEntity == null) {
                    laterDao.insert(laterEntity)
                    Toast.makeText(requireContext(), "Added to your later list", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Already in your later list",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun addWatched() {
        binding.watchedButton.setOnClickListener {
            val watchedEntity = WatchedEntity(
                id = binding.movieDetail?.id,
                overview = binding.movieDetail?.overview,
                posterPath = binding.movieDetail?.posterPath,
                title = binding.movieDetail?.title,
                voteAverage = binding.movieDetail?.voteAverage
            )
            val watchedDao = WatchedDatabase.getInstance(requireContext()).watchedDao()
            lifecycleScope.launch {
                val existingEntity = watchedDao.getWatchedEntityById(watchedEntity.id)
                if (existingEntity == null) {
                    watchedDao.insert(watchedEntity)
                    Toast.makeText(
                        requireContext(),
                        "Added to your watched list",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Already in your watched list",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            }
        }
    }

    private fun handleViewState(dailyState: DailyState) {
        dailyState.movieList.let {
            if (dailyState.isLoading == false) {
                val movieList = dailyState.movieList
                if (movieList.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Movie list is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val random = Random()
                    val randomIndex = random.nextInt(movieList.size)

                    val selectedMovie = movieList[randomIndex]
                    binding.movieDetail = selectedMovie
                    val animation = AnimationUtils.loadAnimation(context, R.anim.rotate)
                    binding.randomFilmCard.startAnimation(animation)

                }
            }

        }

    }

}