package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.quickmenu.QuickMenuItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.quickmenu.QuickMenuListAdapter
import com.dmitrysergeev.weatherapp.presentation.mainscreen.weathercardrecyclerview.WeatherCardListAdapter

class MainScreenItemViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    fun onBind(item: MainScreenItem, viewType: Int){
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
                    val menuItems = (item.data as List<QuickMenuItem>)
                    val quickMenuLayoutManager = GridLayoutManager(root.context,4)
                    quickMenuRecyclerView.layoutManager = quickMenuLayoutManager
                    val quickMenuListAdapter = QuickMenuListAdapter(menuItems)
                    quickMenuRecyclerView.adapter = quickMenuListAdapter
                }
            }
            MainScreenItemListAdapter.TMP_MENU_ITEM_VIEW -> {
                (binding as TmpScreenMenuItemBinding).apply {
                    title.text = item.title
                }
            }
        }
    }

}