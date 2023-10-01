package com.example.filmshakerkotlin.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("movieDetails")
    val results: List<MovieDetailDto?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)