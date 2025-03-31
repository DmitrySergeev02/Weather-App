package com.dmitrysergeev.weatherapp.data.weather.api

import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import javax.inject.Inject

class WeatherRepositoryApiImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getCurrentWeather(query: String): CurrentWeatherResponse {
        return weatherApi.getCurrentWeather(query).toCurrentWeatherResponse()
    }
}