package com.dmitrysergeev.weatherapp.data.weather.api

import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryApiImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override fun getCurrentWeather(query: String): Flow<CurrentWeatherResponse> {
        return flow {
            emit(weatherApi.getCurrentWeather(query).toCurrentWeatherResponse())
        }
    }

    override fun getForecastWeather(query: String, days: Int, dt: String?): Flow<ForecastWeatherResponse> {
        return flow {
            emit(
                weatherApi.getForecastWeather(
                    q = query,
                    days = days,
                    dt = dt
                ).toForecastWeatherResponse()
            )
        }
    }
}