package com.example.filmshakerkotlin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieVideoDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<MovieVideoDetailDto?>?
)
