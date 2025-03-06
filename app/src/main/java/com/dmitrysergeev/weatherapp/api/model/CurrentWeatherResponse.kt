package com.dmitrysergeev.weatherapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    val location: Location,
    @Json(name = "current") val currentWeather: CurrentWeather
)
