package com.example.filmshakerkotlin.ui.screen.categories.trending

import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.example.filmshakerkotlin.domain.model.trending.TrendingDetail

data class TrendingState(
    val movieList: ArrayList<TrendingDetail>? = arrayListOf(),
    val isLoading: Boolean? = true
)
