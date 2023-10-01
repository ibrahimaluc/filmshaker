package com.example.filmshakerkotlin.data.remote

import com.example.filmshakerkotlin.data.util.MovieConstant
import com.example.filmshakerkotlin.domain.model.Movie
import com.example.filmshakerkotlin.domain.model.alldetail.MovieAllDetail
import com.example.filmshakerkotlin.domain.model.soon.Soon
import com.example.filmshakerkotlin.domain.model.trending.Trending
import com.example.filmshakerkotlin.domain.model.youtube.MovieVideo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE,
        @Query("page") page: Int = MovieConstant.randomNum,

        ): Movie

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE,
        @Query("page") page: Int = MovieConstant.randomNum,
    ): Movie

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE

    ): MovieVideo

    @GET("movie/{movie_id}")
    suspend fun getMovieAllDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE

    ): MovieAllDetail

    @GET("movie/upcoming")
    suspend fun getSoon(
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE,
        @Query("page") page: Int = 1,
    ): Soon

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("language") language: String = MovieConstant.LANGUAGE,
        @Query("page") page: Int = 2,
    ): Soon

    @GET("trending/all/day")
    suspend fun getTrending(
        @Query("api_key") apiKey: String = MovieConstant.API_KEY,
        @Query("page") page: Int = 1,
    ): Trending

}