package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Forecast
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastApi(
    @Json(name = "forecastday") val forecastDayApi: List<ForecastDayApi>
) {

    fun toForecast(): Forecast = Forecast(
        forecastDayApi.map {
            it.toForecastDay()
        }
    )

}