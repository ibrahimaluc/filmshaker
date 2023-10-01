package com.example.filmshakerkotlin.data.remote.repository

import com.example.filmshakerkotlin.core.util.Resource
import com.example.filmshakerkotlin.data.remote.MovieApi
import com.example.filmshakerkotlin.domain.model.Movie
import com.example.filmshakerkotlin.domain.model.alldetail.MovieAllDetail
import com.example.filmshakerkotlin.domain.model.soon.Soon
import com.example.filmshakerkotlin.domain.model.trending.Trending
import com.example.filmshakerkotlin.domain.model.youtube.MovieVideo
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getMovies(): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getMovies()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }

    }

    override suspend fun getDailyMovies(): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getTopRated()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<MovieVideo>> = flow {
        emit(Resource.Loading())

        try {

            val response = api.getMovieVideos(movieId)
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }
    }

    override suspend fun getMovieAllDetail(movieId: Int): Flow<Resource<MovieAllDetail>> = flow {
        emit(Resource.Loading())

        try {

            val response = api.getMovieAllDetail(movieId)
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }
    }

    override suspend fun getSoon(): Flow<Resource<Soon>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getSoon()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }

    }

    override suspend fun getNowPlaying(): Flow<Resource<Soon>> = flow {

        emit(Resource.Loading())
        try {
            val response = api.getNowPlaying()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }
    }

    override suspend fun getTrending(): Flow<Resource<Trending>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getTrending()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Http error!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Server error"
                )
            )
        }
    }
}