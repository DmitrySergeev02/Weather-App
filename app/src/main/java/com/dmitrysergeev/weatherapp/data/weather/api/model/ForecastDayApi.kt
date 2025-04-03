package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDayApi(
    @Json(name = "astro") val astroApi: AstroApi,
    val date: String,
    @Json(name = "date_epoch") val dateEpoch: Int,
    @Json(name = "day") val dayApi: DayApi,
    @Json(name = "hour") val hourApi: List<HourApi>
)