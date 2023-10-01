package com.example.filmshakerkotlin.domain.model.youtube


import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<MovieVideoDetail>?
)