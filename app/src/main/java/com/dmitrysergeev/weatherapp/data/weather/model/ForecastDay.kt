package com.dmitrysergeev.weatherapp.data.weather.model

data class ForecastDay(
    val astroApi: Astro,
    val date: String,
    val dateEpoch: Int,
    val dayApi: Day,
    val hourApi: List<Hour>
)