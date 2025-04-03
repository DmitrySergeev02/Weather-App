package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastApi(
    @Json(name = "forecast") val forecastDayApi: List<ForecastDayApi>
)