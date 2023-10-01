package com.example.filmshakerkotlin.ui.screen.categories.soon

import com.example.filmshakerkotlin.domain.model.MovieDetail

data class SoonState(
    val movieList: ArrayList<MovieDetail>? = arrayListOf(),
    val isLoading: Boolean? = true
)