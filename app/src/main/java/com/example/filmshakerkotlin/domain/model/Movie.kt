package com.example.filmshakerkotlin.domain.model
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movieDetails: List<MovieDetail>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)