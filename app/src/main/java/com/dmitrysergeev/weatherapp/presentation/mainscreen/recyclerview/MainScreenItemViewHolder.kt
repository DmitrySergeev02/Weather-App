package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.AirQualityMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.PrecipitationMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.UvIndexMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.VisibilityMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
import com.dmitrysergeev.weatherapp.presentation.views.GradientProgressBar
import com.dmitrysergeev.weatherapp.presentation.mainscreen.airquality.Pm25Data
import com.dmitrysergeev.weatherapp.presentation.mainscreen.quickmenu.QuickMenuListAdapter
import com.dmitrysergeev.weatherapp.presentation.mainscreen.weathercardrecyclerview.WeatherCardListAdapter

class MainScreenItemViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    fun onBind(item: MainScreenMenuItem, viewType: Int){
        when (viewType){
            MainScreenItemListAdapter.WEATHER_CARDS_VIEW -> {
                (binding as WeatherCardsRecyclerViewBinding).apply {
                    val weatherForecasts = (item.data as List<ForecastWeatherResponse>)
                    val cardsLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                    cardsRecyclerView.layoutManager = cardsLayoutManager
                    val adapter = WeatherCardListAdapter(weatherForecasts)
                    cardsRecyclerView.adapter = adapter
                }
            }
            MainScreenItemListAdapter.QUICK_MENU_VIEW -> {
                (binding as QuickMenuRecyclerViewBinding).apply {
                    val menuItems = (item.data as List<MainScreenMenuItem>)
                    val quickMenuLayoutManager = GridLayoutManager(root.context,4)
                    quickMenuRecyclerView.layoutManager = quickMenuLayoutManager
                    val quickMenuListAdapter = QuickMenuListAdapter(menuItems.subList(2,menuItems.size))
                    quickMenuRecyclerView.adapter = quickMenuListAdapter
                }
            }
            MainScreenItemListAdapter.TMP_MENU_ITEM_VIEW -> {
                (binding as TmpScreenMenuItemBinding).apply {
                    title.text = item.title
                }
            }
            MainScreenItemListAdapter.PRECIPITATION_ITEM_VIEW -> {
                val weatherForecasts = (item.data as List<ForecastWeatherResponse>)
                if (weatherForecasts.isEmpty())
                    return
                val currentForecast = weatherForecasts[0]
                (binding as PrecipitationMainScreenItemBinding).apply {
                    humidityItem.title.text = "Humidity"
                    humidityItem.icon.setImageResource(R.drawable.humidity_in_box)
                    humidityItem.value.text = root.context.getString(R.string.percent_value, currentForecast.currentWeather.humidity)
                    rainProbabilityItem.title.text = "Rain Probability"
                    rainProbabilityItem.icon.setImageResource(R.drawable.umbrella_in_box)
                    rainProbabilityItem.value.text = root.context.getString(R.string.percent_value, currentForecast.forecast.forecastDay[0].dayApi.dailyChanceOfRain)
                    dewPointItem.title.text = "Dew Point"
                    dewPointItem.icon.setImageResource(R.drawable.dew_point_in_box)
                    dewPointItem.value.text = root.context.getString(R.string.percent_value, currentForecast.currentWeather.dewPointC.toInt())
                    cloudOverItem.title.text = "Cloud over"
                    cloudOverItem.icon.setImageResource(R.drawable.cloudy_in_box)
                    cloudOverItem.value.text = root.context.getString(R.string.percent_value, currentForecast.currentWeather.cloud)
                }
            }
            MainScreenItemListAdapter.AIR_QUALITY_VIEW -> {
                val weatherForecasts = (item.data as List<ForecastWeatherResponse>)
                if (weatherForecasts.isEmpty())
                    return
                val airQuality = weatherForecasts[0].currentWeather.airQuality
                (binding as AirQualityMainScreenItemBinding).apply {
                    root.setOnClickListener {
                        val value = (Math.random()*100).toInt()
                        Log.d(GradientProgressBar.TAG,value.toString())
                        airQualityProgressBar.setProgress(value,true)
                    }
                    val pm25Data = Pm25Data(airQuality.pm2_5.toInt())

                    levelText.text = root.context.getString(pm25Data.getLevel())
                    levelDescription.text = root.context.getString(pm25Data.getDescription())

                    pm25Text.text = airQuality.pm2_5.toInt().toString()

                    pm25.labelText.text = "PM2.5"
                    pm25.valueText.text = airQuality.pm2_5.toInt().toString()
                    pm25.progressBar.progress = airQuality.pm2_5.toInt()

                    pm10.labelText.text = "PM10"
                    pm10.valueText.text = airQuality.pm10.toInt().toString()
                    pm10.progressBar.progress = airQuality.pm10.toInt()

                    o3.labelText.text = "O3"
                    o3.valueText.text = airQuality.o3.toInt().toString()
                    o3.progressBar.progress = airQuality.o3.toInt()

                    so2.labelText.text = "SO2"
                    so2.valueText.text = airQuality.so2.toInt().toString()
                    so2.progressBar.progress = airQuality.so2.toInt()

                    no2.labelText.text = "NO2"
                    no2.valueText.text = airQuality.no2.toInt().toString()
                    so2.progressBar.progress = airQuality.so2.toInt()

                    co.labelText.text = "Co"
                    co.valueText.text = airQuality.co.toInt().toString()
                    co.progressBar.progress = airQuality.co.toInt()
                }
            }
            MainScreenItemListAdapter.VISIBILITY_VIEW -> {
                val weatherForecasts = (item.data as List<ForecastWeatherResponse>)
                if (weatherForecasts.isEmpty())
                    return
                val visibility = weatherForecasts[0].currentWeather.visKm.toInt()
                (binding as VisibilityMainScreenItemBinding).apply {
                    visibilityValue.text = root.context.getString(R.string.visibility_value_km, visibility)
                }
            }
            MainScreenItemListAdapter.UV_INDEX_VIEW -> {
                val weatherForecasts = (item.data as List<ForecastWeatherResponse>)
                if (weatherForecasts.isEmpty())
                    return
                val uvIndex = weatherForecasts[0].currentWeather.uv
                (binding as UvIndexMainScreenItemBinding).apply {
                    uvIndexValue.text = uvIndex.toString()
                    uvIndexText.text = "Not Moderate"
                }
            }
        }
    }

}