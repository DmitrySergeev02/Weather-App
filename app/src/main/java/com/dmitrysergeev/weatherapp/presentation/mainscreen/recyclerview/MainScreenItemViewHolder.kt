package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.PrecipitationMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
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
        }
    }

}