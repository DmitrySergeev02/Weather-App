package com.dmitrysergeev.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dmitrysergeev.weatherapp.api.WeatherApi
import com.dmitrysergeev.weatherapp.api.WeatherInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder()
            .addInterceptor(WeatherInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()

        val weatherApi: WeatherApi = retrofit.create<WeatherApi>()

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, weatherApi.getCurrentWeather("Moscow"))
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}