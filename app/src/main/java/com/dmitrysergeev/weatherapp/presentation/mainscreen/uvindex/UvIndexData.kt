package com.dmitrysergeev.weatherapp.presentation.mainscreen.uvindex

import com.dmitrysergeev.weatherapp.R

class UvIndexData(
    uvIndexValue: Float
) {

    private var levelId: Int = 0

    init {
        levelId = if (uvIndexValue in 0.0..2.0){
            R.string.uv_low
        } else if (uvIndexValue>2.0 && uvIndexValue<=5.0){
            R.string.uv_moderate
        } else if (uvIndexValue>5.0 && uvIndexValue<=7){
            R.string.uv_high
        } else if (uvIndexValue>7.0 && uvIndexValue<=10.0){
            R.string.uv_very_high
        } else {
            R.string.uv_extreme_danger
        }
    }

    fun getLevel(): Int {
        return levelId
    }

}