package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Day
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayApi(
    @Json(name = "avghumidity") val avgHumidity: Int,
    @Json(name = "avgtemp_c") val avgTempC: Double,
    @Json(name = "avgtemp_f") val avgTempF: Double,
    @Json(name = "avgvis_km") val avgVisKm: Double,
    @Json(name = "avgvis_miles") val avgVisMiles: Double,
    @Json(name = "condition") val conditionApi: ConditionApi,
    @Json(name = "daily_chance_of_rain") val dailyChanceOfRain: Int,
    @Json(name = "daily_chance_of_snow") val dailyChanceOfSnow: Int,
    @Json(name = "daily_will_it_rain") val dailyWillItRain: Int,
    @Json(name = "daily_will_it_snow") val dailyWillItSnow: Int,
    @Json(name = "maxtemp_c") val maxTempC: Double,
    @Json(name = "maxtemp_f") val maxTempF: Double,
    @Json(name = "maxwind_kph") val maxWindKph: Double,
    @Json(name = "maxwind_mph") val maxWindMph: Double,
    @Json(name = "mintemp_c") val minTempC: Double,
    @Json(name = "mintemp_f") val minTempF: Double,
    @Json(name = "totalprecip_in") val totalPrecipIn: Double,
    @Json(name = "totalprecip_mm") val totalPrecipMm: Double,
    @Json(name = "totalsnow_cm") val totalSnowCm: Double,
    val uv: Double
) {

    fun toDay(): Day = Day(
        avgHumidity,
        avgTempC,
        avgTempF,
        avgVisKm,
        avgVisMiles,
        conditionApi.toCondition(),
        dailyChanceOfRain,
        dailyChanceOfSnow,
        dailyWillItRain,
        dailyWillItSnow,
        maxTempC,
        maxTempF,
        maxWindKph,
        maxWindMph,
        minTempC,
        minTempF,
        totalPrecipIn,
        totalPrecipMm,
        totalSnowCm,
        uv
    )

}