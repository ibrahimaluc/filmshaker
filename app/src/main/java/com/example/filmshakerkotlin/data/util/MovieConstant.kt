package com.example.filmshakerkotlin.data.util

import com.example.filmshakerkotlin.BuildConfig
import kotlin.random.Random

object MovieConstant {

    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY
    const val LANGUAGE = BuildConfig.LANGUAGE
    const val ADD_APP_PROD_KEY = BuildConfig.ADD_APP_PROD_KEY
    var randomNum = Random.nextInt(1, 501)


}