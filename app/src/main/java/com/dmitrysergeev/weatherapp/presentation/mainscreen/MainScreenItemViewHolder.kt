package com.dmitrysergeev.weatherapp.presentation.mainscreen

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.menu.QuickMenuItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.menu.QuickMenuListAdapter
import com.dmitrysergeev.weatherapp.presentation.mainscreen.weathercardrecyclerview.WeatherCardListAdapter

class MainScreenItemViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    fun onBind(item: MainScreenItem, viewType: Int){
        when (viewType){
            MainScreenItemListAdapter.WEATHER_CARDS_VIEW -> {
                (binding as WeatherCardsRecyclerViewBinding).apply {
                    val weatherForecasts = (item.data as List<ForecastWeatherResponse>)

                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(cardsRecyclerView)

                    val cardsLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                    cardsRecyclerView.layoutManager = cardsLayoutManager
                    val adapter = WeatherCardListAdapter(weatherForecasts)
                    cardsRecyclerView.adapter = adapter

                    cardsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)

                            val listSize = adapter.listSize()
                            val firstVisiblePosition = cardsLayoutManager.findFirstVisibleItemPosition()
                            if (firstVisiblePosition > listSize && firstVisiblePosition%listSize == 0) {
                                recyclerView.scrollToPosition(listSize)
                            } else if (firstVisiblePosition == listSize - 1){
                                recyclerView.scrollToPosition(listSize*2)
                            }
                        }
                    })
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