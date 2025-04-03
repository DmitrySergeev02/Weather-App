package com.dmitrysergeev.weatherapp.data.weather

import com.dmitrysergeev.weatherapp.data.weather.api.model.WeatherResponseApi

interface WeatherRepository {

    suspend fun getCurrentWeather(query: String): WeatherResponseApi

}