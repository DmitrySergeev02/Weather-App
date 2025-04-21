package com.dmitrysergeev.weatherapp.presentation.mainscreen.airquality

import com.dmitrysergeev.weatherapp.R

class Pm25Data(
    val data: Int,
) {

    private var levelId: Int = 0
    private var descriptionId: Int = 0

    init {
        if (data in 0..50) {
            levelId = R.string.air_quality_level_good
            descriptionId = R.string.air_quality_level_good_description
        } else if (data in 51..100) {
            levelId = R.string.air_quality_level_medium
            descriptionId = R.string.air_quality_level_medium_description
        } else if (data in 101..150) {
            levelId = R.string.air_quality_level_allegergenic
            descriptionId = R.string.air_quality_level_allegergenic_description
        } else if (data in 151..200) {
            levelId = R.string.air_quality_level_unhealthy
            descriptionId = R.string.air_quality_level_unhealthy
        } else if (data in 201..300) {
            levelId = R.string.air_quality_level_very_unhealthy
            descriptionId = R.string.air_quality_level_very_unhealthy_description
        } else if (data>300) {
            levelId = R.string.air_quality_level_dangerous
            descriptionId = R.string.air_quality_level_dangerous_description
        }
    }

    fun getLevel():Int = levelId

    fun getDescription(): Int = descriptionId

}