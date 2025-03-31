package com.dmitrysergeev.weatherapp.data.weather

import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse

interface WeatherRepository {

    suspend fun getCurrentWeather(query: String): CurrentWeatherResponse

}