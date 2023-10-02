package com.example.filmshakerkotlin.ui.screen.poster


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.core.adapter.ShakeListener
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.core.util.ShakeControl
import com.example.filmshakerkotlin.data.local.later.LaterDatabase
import com.example.filmshakerkotlin.data.local.later.LaterEntity
import com.example.filmshakerkotlin.data.local.watched.WatchedDatabase
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity
import com.example.filmshakerkotlin.databinding.FragmentPosterBinding
import com.example.filmshakerkotlin.domain.model.MovieDetail

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Random

@AndroidEntryPoint
class PosterFragment : BaseFragment<PosterViewModel, FragmentPosterBinding>(
    PosterViewModel::class.java,
    FragmentPosterBinding::inflate
) {
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var shakeListener: ShakeListener
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private var currentMovie: MovieDetail? = null


    override fun onCreateViewInvoke() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest(::handleViewState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (currentMovie != null) {
            binding.movieDetail = currentMovie
            binding.randomFilmCard.visibility = View.VISIBLE
        } else {
            viewModel.getRandMovies()
        }
        bottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE
        posterButtonClick()
        shake()
        addLater()
        addWatched()
        info()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                shakeListener.stopListening()
                ShakeControl.isShaked = false
                bottomNavigationView?.visibility = View.VISIBLE
                isEnabled = false
                activity?.onBackPressed()

            }

        })

    }

    private fun shake() {
        shakeListener = ShakeListener(requireContext()) {
            shakeListener.startListening()
            bottomNavigationView?.visibility = View.INVISIBLE
            currentMovie = null
            viewModel.getRandMovies()
        }
    }

    private fun posterButtonClick() {
        binding.afisImageButton.setOnClickListener {
            val selectedMovie = binding.movieDetail?.id
            currentMovie = binding.movieDetail
            val action =
                PosterFragmentDirections.actionPosterFragmentToMovieDetailFragment(selectedMovie!!)
            findNavController().navigate(action)
        }
    }

    private fun info() {
        binding.posterInfo.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.item_bottom_dialog, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetDialog.behavior.skipCollapsed = true
        bottomSheetView.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
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

    private fun handleViewState(posterState: PosterState?) {
        posterState?.movieList.let {
            if (posterState?.isLoading == false) {
                val movieList = posterState.movieList
                if (movieList.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Movie list is empty!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (currentMovie != null) {
                        binding.movieDetail = currentMovie
                        binding.randomFilmCard.visibility = View.VISIBLE
                    } else {

                        val random = Random()
                        val randomIndex = random.nextInt(movieList.size)

                        val selectedMovie = movieList[randomIndex]
                        binding.movieDetail = selectedMovie
                        binding.randomFilmCard.visibility = View.VISIBLE
                        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
                        binding.randomFilmCard.startAnimation(animation)
                    }
                }
            }
        }
    }
}
