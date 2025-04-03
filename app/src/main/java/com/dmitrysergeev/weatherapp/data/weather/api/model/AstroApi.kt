package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Astro
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
) {

    fun toAstro(): Astro = Astro(
        isMoonUp,
        isSunUp,
        moonIllumination,
        moonPhase,
        moonrise,
        moonset,
        sunrise,
        sunset
    )

}