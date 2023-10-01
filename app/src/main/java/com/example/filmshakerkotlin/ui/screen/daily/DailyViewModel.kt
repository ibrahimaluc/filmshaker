package com.example.filmshakerkotlin.ui.screen.daily

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DailyViewModel @Inject constructor(
    private val movieRepository: MovieRepository,

    ) : BaseViewModel() {

    private val _state: MutableStateFlow<DailyState> =
        MutableStateFlow(DailyState(isLoading = false))
    val state: StateFlow<DailyState> get() = _state

    private var lastFetchedDate: LocalDate? = null
    private var lastFetchedMovie: MovieDetail? = null
    private lateinit var sharedPreferences: SharedPreferences
    private val LAST_FETCHED_DATE_KEY = "last_fetched_date"
    private val LAST_FETCHED_MOVIE_KEY = "last_fetched_movie"


    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }


    private fun loadLastFetchedData() {
        val savedDate = sharedPreferences.getString(LAST_FETCHED_DATE_KEY, null)
        savedDate?.let {
            lastFetchedDate = LocalDate.parse(savedDate)
        }

        val savedMovieJson = sharedPreferences.getString(LAST_FETCHED_MOVIE_KEY, null)
        savedMovieJson?.let {
            lastFetchedMovie = MovieDetail.fromJson(savedMovieJson)
        }
    }

    private fun saveLastFetchedData() {
        lastFetchedDate?.let {
            sharedPreferences.edit()?.putString(LAST_FETCHED_DATE_KEY, it.toString())?.apply()

        }

        lastFetchedMovie?.let {
            val movieJson = it.toJson()
            sharedPreferences.edit()?.putString(LAST_FETCHED_MOVIE_KEY, movieJson)?.apply()
        }
    }


    fun isSameDay() {
        loadLastFetchedData()

        if (lastFetchedDate != null && lastFetchedDate == LocalDate.now() && lastFetchedMovie != null) {
            _state.value = state.value.copy(
                movieList = arrayListOf(lastFetchedMovie!!),
                isLoading = false
            )
        } else {
            fetchDailyMovies()
        }
    }

    private fun fetchDailyMovies() {
        job = viewModelScope.launch {
            movieRepository.getDailyMovies()
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            val movieList = it.data?.movieDetails as ArrayList<MovieDetail>
                            if (movieList.isNotEmpty()) {
                                val randomIndex = (0 until movieList.size).random()
                                val selectedMovie = movieList[randomIndex]
                                lastFetchedDate = LocalDate.now()
                                lastFetchedMovie = selectedMovie
                                saveLastFetchedData()

                                _state.value = state.value.copy(
                                    movieList = arrayListOf(selectedMovie),
                                    isLoading = false
                                )
                            }
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