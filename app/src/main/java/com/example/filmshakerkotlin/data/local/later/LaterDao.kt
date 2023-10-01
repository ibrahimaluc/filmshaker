package com.example.filmshakerkotlin.data.local.later

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LaterDao {
    @Insert
    suspend fun insert(later: LaterEntity)

    @Query("SELECT * FROM later")
    suspend fun getLaterMovies(): List<LaterEntity>

    @Query("SELECT * FROM later WHERE id = :id")
    suspend fun getLaterEntityById(id: Int?): LaterEntity?
}