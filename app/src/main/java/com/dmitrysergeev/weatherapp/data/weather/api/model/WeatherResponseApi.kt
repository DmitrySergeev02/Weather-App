package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseApi(
    @Json(name = "current") val currentApi: CurrentApi?,
    @Json(name = "forecast") val forecastApi: ForecastApi?,
    @Json(name = "location") val locationApi: LocationApi?
)