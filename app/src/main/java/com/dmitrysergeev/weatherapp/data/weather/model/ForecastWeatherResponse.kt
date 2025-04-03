package com.dmitrysergeev.weatherapp.data.weather.model

data class ForecastWeatherResponse(
    val location: Location,
    val currentWeather: CurrentWeather,
    val forecast: Forecast
)