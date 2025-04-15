package com.dmitrysergeev.weatherapp.data.weather.model

data class AirQuality(
    val co: Float,
    val o3: Float,
    val no2: Float,
    val so2: Float,
    val pm2_5: Float,
    val pm10: Float,
    val usEpaIndex: Int,
    val gbDefraIndex: Int
)