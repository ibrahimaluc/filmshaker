package com.example.filmshakerkotlin.ui.screen.categories.trending

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.filmshakerkotlin.core.base.BaseViewModel
import com.example.filmshakerkotlin.core.util.Resource

import com.example.filmshakerkotlin.domain.model.trending.TrendingDetail
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _state: MutableStateFlow<TrendingState> =
        MutableStateFlow(TrendingState(isLoading = false))
    val state: StateFlow<TrendingState> get() = _state


    fun getTrendingMovies() {
        job?.cancel()
        job = viewModelScope.launch {
            movieRepository.getTrending().onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            movieList = it.data?.results as ArrayList<TrendingDetail>,
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