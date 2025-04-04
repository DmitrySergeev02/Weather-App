package com.dmitrysergeev.weatherapp.data.weather

import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCurrentWeather(query: String): Flow<CurrentWeatherResponse>

    fun getForecastWeather(query: String, days:Int, dt: String?=null): Flow<ForecastWeatherResponse>

}