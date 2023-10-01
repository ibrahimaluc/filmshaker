package com.example.filmshakerkotlin.domain.repository

import com.example.filmshakerkotlin.core.util.Resource
import com.example.filmshakerkotlin.domain.model.Movie
import com.example.filmshakerkotlin.domain.model.alldetail.MovieAllDetail
import com.example.filmshakerkotlin.domain.model.soon.Soon
import com.example.filmshakerkotlin.domain.model.trending.Trending
import com.example.filmshakerkotlin.domain.model.youtube.MovieVideo
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getMovies(): Flow<Resource<Movie>>
    suspend fun getDailyMovies(): Flow<Resource<Movie>>
    suspend fun getMovieVideos(movieId: Int): Flow<Resource<MovieVideo>>
    suspend fun getMovieAllDetail(movieId: Int): Flow<Resource<MovieAllDetail>>
    suspend fun getSoon(): Flow<Resource<Soon>>

    suspend fun getNowPlaying(): Flow<Resource<Soon>>

    suspend fun getTrending():Flow<Resource<Trending>>
}