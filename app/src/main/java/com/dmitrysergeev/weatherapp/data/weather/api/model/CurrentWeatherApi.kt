package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherApi(
    @Json(name = "last_updated_epoch") val lastUpdatedEpoch: Long,
    @Json(name = "last_updated") val lastUpdated: String,
    @Json(name = "temp_c") val tempC: Float,
    @Json(name = "temp_f") val tempF: Float,
    @Json(name = "is_day") val isDay: Int,
    @Json(name = "condition") val conditionApi: ConditionApi
) {

    fun toCurrentWeather(): CurrentWeather{
        return CurrentWeather(
            lastUpdatedEpoch,
            lastUpdated,
            tempC,
            tempF,
            isDay,
            conditionApi.toCondition()
        )
    }

}