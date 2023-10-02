package com.example.filmshakerkotlin.ui.screen.categories.nowplaying

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.filmshakerkotlin.core.base.BaseViewModel
import com.example.filmshakerkotlin.core.util.Resource
import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import com.example.filmshakerkotlin.ui.screen.categories.soon.SoonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _state: MutableStateFlow<NowPlayingState> =
        MutableStateFlow(NowPlayingState(isLoading = false))
    val state: StateFlow<NowPlayingState> get() = _state


    fun getNowPlayingMovies() {
        job?.cancel()
        job = viewModelScope.launch {
            movieRepository.getNowPlaying().onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            movieList = it.data?.results as ArrayList<MovieDetail>,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        val errorMessage = it.message ?: "Unknown error"
                        Log.e("MovieRepository", errorMessage)

                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}