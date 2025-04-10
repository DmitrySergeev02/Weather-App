package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import androidx.appcompat.content.res.AppCompatResources
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
                (binding as PrecipitationMainScreenItemBinding).apply {
                    humidityItem.setTitle("Humidity")
                    humidityItem.setIconSrc(AppCompatResources.getDrawable(root.context,R.drawable.humidity))
                    humidityItem.value.text = "88%"
                    rainProbabilityItem.setTitle("Rain Probability")
                    rainProbabilityItem.setIconSrc(AppCompatResources.getDrawable(root.context,R.drawable.humidity))
                    rainProbabilityItem.value.text = "88%"
                    dewPointItem.setTitle("Dew Point")
                    dewPointItem.setIconSrc(AppCompatResources.getDrawable(root.context,R.drawable.humidity))
                    dewPointItem.value.text = "88%"
                    cloudOverItem.setTitle("Cloud over")
                    cloudOverItem.setIconSrc(AppCompatResources.getDrawable(root.context,R.drawable.humidity))
                    cloudOverItem.value.text = "88%"
                }
            }
        }
    }

}