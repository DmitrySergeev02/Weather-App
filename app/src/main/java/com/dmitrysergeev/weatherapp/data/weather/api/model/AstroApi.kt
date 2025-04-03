package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AstroApi(
    @Json(name = "is_moon_up") val isMoonUp: Int,
    @Json(name = "is_sun_up") val isSunUp: Int,
    @Json(name = "moon_illumination") val moonIllumination: Double,
    @Json(name = "moon_phase") val moonPhase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)