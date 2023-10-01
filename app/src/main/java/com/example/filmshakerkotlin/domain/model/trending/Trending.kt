package com.example.filmshakerkotlin.domain.model.trending


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Trending(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TrendingDetail>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)