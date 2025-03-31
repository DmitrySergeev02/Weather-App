package com.dmitrysergeev.weatherapp.data.weather.model

data class CurrentWeather(
    val lastUpdatedEpoch: Long,
    val lastUpdated: String,
    val tempC: Float,
    val tempF: Float,
    val isDay: Int,
    val conditionApi: Condition
)