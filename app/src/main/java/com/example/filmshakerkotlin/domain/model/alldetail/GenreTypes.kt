package com.example.filmshakerkotlin.domain.model.alldetail


import com.google.gson.annotations.SerializedName

data class GenreTypes(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)