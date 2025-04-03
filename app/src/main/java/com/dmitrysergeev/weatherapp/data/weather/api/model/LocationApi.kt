package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Location
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationApi(
    val name: String,
    val region: String,
    val country: String,
) {

    fun toLocation(): Location{
        return Location(
            name,
            region,
            country
        )
    }

}
