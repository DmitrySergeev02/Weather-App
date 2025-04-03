package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.api.model.WeatherResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private var currentWeatherResponse: WeatherResponseApi? = null

    init {
        viewModelScope.launch {
            currentWeatherResponse = weatherRepository.getCurrentWeather("Moscow")
            Log.d("MainScreenViewModel", currentWeatherResponse.toString())
        }
    }

    fun test(){
        Log.d("MainScreenViewModel", "123")
    }

}