package com.dmitrysergeev.weatherapp.api

import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6acd4a1405454811ada11545250603"

interface WeatherApi {

    @GET("current.json?key=${API_KEY}")
    suspend fun getCurrentWeather(
        @Query("q") q: String
    ): String

}