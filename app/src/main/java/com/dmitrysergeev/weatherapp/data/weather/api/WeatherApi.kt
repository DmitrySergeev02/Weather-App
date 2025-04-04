package com.dmitrysergeev.weatherapp.data.weather.api

import com.dmitrysergeev.weatherapp.data.weather.api.model.WeatherResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String
    ): WeatherResponseApi

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("dt") dt: String?
    ): WeatherResponseApi

}