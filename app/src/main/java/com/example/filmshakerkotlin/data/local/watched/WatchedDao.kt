package com.example.filmshakerkotlin.data.local.watched

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WatchedDao {
    @Insert
    suspend fun insert(watched: WatchedEntity)

    @Query("SELECT * FROM watched")
    suspend fun getWatchedMovies(): List<WatchedEntity>

    @Query("SELECT * FROM watched WHERE id = :id")
    suspend fun getWatchedEntityById(id: Int?): WatchedEntity?
}