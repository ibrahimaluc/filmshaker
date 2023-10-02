package com.example.filmshakerkotlin.ui.screen.poster

import androidx.lifecycle.viewModelScope
import com.example.filmshakerkotlin.core.base.BaseViewModel
import com.example.filmshakerkotlin.core.util.Resource
import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PosterViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _state: MutableStateFlow<PosterState> =
        MutableStateFlow(PosterState(isLoading = false))
    val state: StateFlow<PosterState> get() = _state

    fun getRandMovies() {
        job = viewModelScope.launch {
            movieRepository.getMovies()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                movieList = result.data?.movieDetails as ArrayList<MovieDetail>,
                                isLoading = false
                            )

                        }

                        is Resource.Error -> {
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