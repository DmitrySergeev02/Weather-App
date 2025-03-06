package com.dmitrysergeev.weatherapp.api

import retrofit2.http.GET

const val API_KEY = "6acd4a1405454811ada11545250603"

interface WeatherApi {

    @GET("current.json?q=Paris&key=${API_KEY}&aqi=no")
    suspend fun getWeather(): String

}