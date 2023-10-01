package com.example.filmshakerkotlin.ui.screen.categories.nowplaying

import com.example.filmshakerkotlin.domain.model.MovieDetail

data class NowPlayingState(
    val movieList: ArrayList<MovieDetail>? = arrayListOf(),
    val isLoading: Boolean? = true
)
