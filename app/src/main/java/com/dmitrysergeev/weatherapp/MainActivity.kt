package com.dmitrysergeev.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.api.WeatherApi
import com.dmitrysergeev.weatherapp.data.weather.api.WeatherInterceptor
import com.dmitrysergeev.weatherapp.data.weather.api.WeatherRepositoryApiImpl
import com.dmitrysergeev.weatherapp.presentation.mainscreen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var repo: WeatherRepository

    private val mainScreenViewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "${repo.getCurrentWeather("Moscow")}")
        }

        mainScreenViewModel.test()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}