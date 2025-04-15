package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.AirQuality
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQualityApi(
    val co: Float,
    val o3: Float,
    val no2: Float,
    val so2: Float,
    val pm2_5: Float,
    val pm10: Float,
    @Json(name = "us-epa-index") val usEpaIndex: Int,
    @Json(name = "gb-defra-index") val gbDefraIndex: Int
) {

    fun toAirQuality(): AirQuality{
        return AirQuality(
            co,
            o3,
            no2,
            so2,
            pm2_5,
            pm10,
            usEpaIndex,
            gbDefraIndex
        )
    }

}