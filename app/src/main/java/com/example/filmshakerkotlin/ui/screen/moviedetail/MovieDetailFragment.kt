package com.example.filmshakerkotlin.ui.screen.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.filmshakerkotlin.core.adapter.YoutubePlayerManager
import com.example.filmshakerkotlin.core.base.BaseFragment
import com.example.filmshakerkotlin.databinding.FragmentMovieDetailBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    MovieDetailViewModel::class.java,
    FragmentMovieDetailBinding::inflate
) {

    private lateinit var yPM: YoutubePlayerManager
    private lateinit var yId: String
    private var selectedMovie: Int? = null
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreateViewInvoke() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailState.collectLatest(::handleViewState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        selectedMovie = arg?.getInt("selectedMovie")
        showAdd()
        if (selectedMovie != null) {
            viewModel.getMovieVideos(selectedMovie!!)
            viewModel.getMovieAllDetail(selectedMovie!!)
        }


    }

    private fun handleViewState(detailState: MovieDetailState) {
        detailState.videoList.let {
            if (detailState.isLoading == false) {
                val videoList = detailState.videoList
                val officialTrailer =
                    videoList?.find { it.name?.contains("Official Trailer") == true }
                val videoToShow = officialTrailer ?: videoList?.firstOrNull()

                videoToShow?.let {
                    yId = it.key.toString()
                    yPM =
                        YoutubePlayerManager(
                            binding.playerview,
                            viewLifecycleOwner.lifecycle,
                            yId
                        )
                    yPM.initPlayer()
                    Toast.makeText(requireContext(), "video loading", Toast.LENGTH_SHORT).show()
                }

            }
        }
        detailState.movieDetail?.let {
            binding.movieAllDetail = it
        }
    }

    @SuppressLint("VisibleForTests")
    private fun showAdd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireContext(),
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adError.toString().let { it1 -> Log.d("TAG", it1) }

                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("TAG", "loaded")
                    mInterstitialAd = interstitialAd
                    mInterstitialAd.show(requireActivity())
                }

            })
    }

}






