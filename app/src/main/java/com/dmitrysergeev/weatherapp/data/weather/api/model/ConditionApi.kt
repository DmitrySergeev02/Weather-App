package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Condition
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionApi(
    val text: String,
    @Json(name = "icon") val iconUrl: String,
    val code: Int
) {

    fun toCondition(): Condition{
        return Condition(
            text,
            iconUrl,
            code
        )
    }

}
