package com.example.filmshakerkotlin.ui.screen.moviedetail

import androidx.lifecycle.viewModelScope
import com.example.filmshakerkotlin.core.base.BaseViewModel
import com.example.filmshakerkotlin.core.util.Resource
import com.example.filmshakerkotlin.domain.model.alldetail.MovieAllDetail
import com.example.filmshakerkotlin.domain.model.youtube.MovieVideoDetail
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {


    private val _detailState: MutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState(isLoading = false))
    val detailState: StateFlow<MovieDetailState> get() = _detailState


    fun getMovieVideos(movieId: Int) {
        job = viewModelScope.launch {
            movieRepository.getMovieVideos(movieId)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _detailState.value = MovieDetailState(
                                videoList = result.data?.results as ArrayList<MovieVideoDetail>,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _detailState.value = MovieDetailState(
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _detailState.value = MovieDetailState(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }
    fun getMovieAllDetail(movieId: Int) {
        job = viewModelScope.launch {
            movieRepository.getMovieAllDetail(movieId)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _detailState.value = MovieDetailState(
                                movieDetail = result.data,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _detailState.value =MovieDetailState(
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _detailState.value = MovieDetailState(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }


}