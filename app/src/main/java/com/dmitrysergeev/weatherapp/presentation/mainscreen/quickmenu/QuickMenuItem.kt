package com.dmitrysergeev.weatherapp.presentation.mainscreen.menu

import androidx.annotation.DrawableRes

data class QuickMenuItem(
    val title: String,
    @DrawableRes val iconId: Int,
    val doOnClick: ()->Unit
)
