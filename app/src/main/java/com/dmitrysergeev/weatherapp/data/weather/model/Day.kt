package com.dmitrysergeev.weatherapp.data.weather.model

data class Day(
    val avgHumidity: Int,
    val avgTempC: Double,
    val avgTempF: Double,
    val avgVisKm: Double,
    val avgVisMiles: Double,
    val conditionApi: Condition,
    val dailyChanceOfRain: Int,
    val dailyChanceOfSnow: Int,
    val dailyWillItRain: Int,
    val dailyWillItSnow: Int,
    val maxTempC: Double,
    val maxTempF: Double,
    val maxWindKph: Double,
    val maxWindMph: Double,
    val minTempC: Double,
    val minTempF: Double,
    val totalPrecipIn: Double,
    val totalPrecipMm: Double,
    val totalSnowCm: Double,
    val uv: Double
)
