package com.dmitrysergeev.weatherapp.data.weather.model

data class Astro(
    val isMoonUp: Int,
    val isSunUp: Int,
    val moonIllumination: Double,
    val moonPhase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)
