package com.dmitrysergeev.weatherapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String,
    @Json(name = "icon") val iconUrl: String,
    val code: Int
)
