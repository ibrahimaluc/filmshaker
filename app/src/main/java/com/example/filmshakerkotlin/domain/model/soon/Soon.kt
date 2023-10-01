package com.example.filmshakerkotlin.domain.model.soon


import com.example.filmshakerkotlin.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class Soon(
    @SerializedName("dates")
    val dates: Dates?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieDetail>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)