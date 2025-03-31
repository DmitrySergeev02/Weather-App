package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionApi(
    val text: String,
    @Json(name = "icon") val iconUrl: String,
    val code: Int
)
