package com.example.filmshakerkotlin.di

import com.example.filmshakerkotlin.data.remote.MovieApi
import com.example.filmshakerkotlin.data.remote.repository.MovieRepositoryImpl
import com.example.filmshakerkotlin.data.util.MovieConstant
import com.example.filmshakerkotlin.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(MovieConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }
}