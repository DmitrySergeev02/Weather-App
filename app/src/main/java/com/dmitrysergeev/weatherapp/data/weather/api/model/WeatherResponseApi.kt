package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseApi(
    @Json(name = "current") val currentApi: CurrentApi?,
    @Json(name = "forecast") val forecastApi: ForecastApi?,
    @Json(name = "location") val locationApi: LocationApi?
) {

    fun toCurrentWeatherResponse(): CurrentWeatherResponse = CurrentWeatherResponse(
        locationApi!!.toLocation(),
        currentApi!!.toCurrentWeather()
    )

    fun toForecastWeatherResponse(): ForecastWeatherResponse = ForecastWeatherResponse(
        locationApi!!.toLocation(),
        currentApi!!.toCurrentWeather(),
        forecastApi!!.toForecast()
    )

}