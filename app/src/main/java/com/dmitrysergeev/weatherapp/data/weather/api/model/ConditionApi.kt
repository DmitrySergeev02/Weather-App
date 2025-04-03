package com.dmitrysergeev.weatherapp.data.weather.api.model

import com.dmitrysergeev.weatherapp.data.weather.model.Condition
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionApi(
    val code: Int,
    val icon: String,
    val text: String
) {

    fun toCondition(): Condition = Condition(
        code = code,
        icon = icon,
        text = text
    )

}