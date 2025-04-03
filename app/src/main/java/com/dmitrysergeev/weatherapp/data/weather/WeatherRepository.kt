package com.dmitrysergeev.weatherapp.data.weather

import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse

interface WeatherRepository {

    suspend fun getCurrentWeather(query: String): CurrentWeatherResponse

    suspend fun getForecastWeather(query: String, days:Int, dt: String): ForecastWeatherResponse

}