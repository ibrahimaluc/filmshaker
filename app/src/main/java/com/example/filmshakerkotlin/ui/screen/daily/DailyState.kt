package com.example.filmshakerkotlin.ui.screen.daily

import com.example.filmshakerkotlin.domain.model.MovieDetail

data class DailyState(
    val movieList: ArrayList<MovieDetail>? = arrayListOf(),
    val isLoading: Boolean? = true,

    )