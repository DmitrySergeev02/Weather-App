package com.dmitrysergeev.weatherapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String
    ): String

}