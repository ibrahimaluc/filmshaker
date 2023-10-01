package com.example.filmshakerkotlin.ui.screen.poster

import com.example.filmshakerkotlin.domain.model.MovieDetail

data class PosterState(
    val movieList: ArrayList<MovieDetail>? = arrayListOf(),
    val isLoading: Boolean? = true
)