package com.example.filmshakerkotlin.ui.screen.moviedetail

import com.example.filmshakerkotlin.domain.model.alldetail.MovieAllDetail
import com.example.filmshakerkotlin.domain.model.youtube.MovieVideoDetail

data class MovieDetailState(
    val videoList: ArrayList<MovieVideoDetail>? = arrayListOf(),
    val movieDetail: MovieAllDetail? = null,
    val isLoading: Boolean? = true
)


