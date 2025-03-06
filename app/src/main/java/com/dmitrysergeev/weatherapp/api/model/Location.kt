package com.dmitrysergeev.weatherapp.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val name: String,
    val region: String,
    val country: String,
)
