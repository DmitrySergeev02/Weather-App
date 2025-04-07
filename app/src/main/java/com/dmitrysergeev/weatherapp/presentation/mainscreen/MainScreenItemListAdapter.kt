package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.menu.QuickMenuItem

class MainScreenItemViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    fun onBind(item: MainScreenItem, viewType: Int){

    }

}

class MainScreenItemListAdapter(
    private val menuItemList: List<MainScreenItem>
): RecyclerView.Adapter<MainScreenItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewBinding? = null
        when (viewType){
            WEATHER_CARDS_VIEW -> {
                binding = WeatherCardsRecyclerViewBinding.inflate(inflater,parent,false)
            }
            QUICK_MENU_VIEW -> {
                binding = QuickMenuRecyclerViewBinding.inflate(inflater, parent, false)
            }
            TMP_MENU_ITEM_VIEW -> {
                binding = TmpScreenMenuItemBinding.inflate(inflater,parent,false)
            }
        }
        return MainScreenItemViewHolder(binding!!)
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = menuItemList[position]
        return if (currentItem.data is List<*>){
            when(currentItem.data[0]){
                is ForecastWeatherResponse -> WEATHER_CARDS_VIEW
                is QuickMenuItem -> QUICK_MENU_VIEW
                else -> -1
            }
        } else {
            when(currentItem.data){
                is String -> TMP_MENU_ITEM_VIEW
                else -> -1
            }
        }
    }

    override fun onBindViewHolder(holder: MainScreenItemViewHolder, position: Int) {
        val item = menuItemList[position]
        val itemViewType = getItemViewType(position)
        holder.onBind(item, itemViewType)
    }

    override fun getItemCount(): Int = menuItemList.size

    companion object {
        const val WEATHER_CARDS_VIEW = 0
        const val QUICK_MENU_VIEW = 1
        const val TMP_MENU_ITEM_VIEW = 2
    }
}