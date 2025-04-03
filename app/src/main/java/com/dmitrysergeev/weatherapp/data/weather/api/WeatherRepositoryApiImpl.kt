package com.dmitrysergeev.weatherapp.data.weather.api

import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.api.model.WeatherResponseApi
import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import javax.inject.Inject

class WeatherRepositoryApiImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getCurrentWeather(query: String): CurrentWeatherResponse {
        return weatherApi.getCurrentWeather(query).toCurrentWeatherResponse()
    }

    override suspend fun getForecastWeather(query: String, days: Int, dt: String): ForecastWeatherResponse {
        return weatherApi.getForecastWeather(
            q = query,
            days = days,
            dt = dt
        ).toForecastWeatherResponse()
    }
}