package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import androidx.annotation.DrawableRes

data class MainScreenMenuItem(
    val title: String,
    @DrawableRes val iconId: Int,
    val doOnClick: ()->Unit,
    val data: Any
)
