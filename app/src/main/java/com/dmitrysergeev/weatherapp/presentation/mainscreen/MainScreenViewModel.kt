package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.api.model.WeatherResponseApi
import com.dmitrysergeev.weatherapp.data.weather.model.CurrentWeatherResponse
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _forecast: MutableStateFlow<ForecastWeatherResponse?> = MutableStateFlow(null)
    val forecast: StateFlow<ForecastWeatherResponse?> = _forecast.asStateFlow()

    init {
        viewModelScope.launch {
            weatherRepository.getForecastWeather("Saint-Petersburg",3,"2025-04-04").collect{
                _forecast.value = it
            }
        }
    }

    fun test(){
        Log.d("MainScreenViewModel", "123")
    }

}