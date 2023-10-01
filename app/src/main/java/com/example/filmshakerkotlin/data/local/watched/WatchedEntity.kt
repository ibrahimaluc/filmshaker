package com.example.filmshakerkotlin.data.local.watched

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched")
data class WatchedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
)
