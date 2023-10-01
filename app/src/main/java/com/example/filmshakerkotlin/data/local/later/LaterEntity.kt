package com.example.filmshakerkotlin.data.local.later

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "later")
data class LaterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
)
