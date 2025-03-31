package com.dmitrysergeev.weatherapp.data.weather.model

data class CurrentWeatherResponse(
    val location: Location,
    val currentWeather: CurrentWeather
)