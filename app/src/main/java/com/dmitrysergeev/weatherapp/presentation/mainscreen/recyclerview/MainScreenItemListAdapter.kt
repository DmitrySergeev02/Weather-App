package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dmitrysergeev.weatherapp.databinding.AirQualityMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.PrecipitationMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.QuickMenuRecyclerViewBinding
import com.dmitrysergeev.weatherapp.databinding.TmpScreenMenuItemBinding
import com.dmitrysergeev.weatherapp.databinding.UvIndexMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.VisibilityMainScreenItemBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardsRecyclerViewBinding
class MainScreenItemListAdapter(
    private var menuItemList: List<MainScreenMenuItem>,
    private val fragmentManager: FragmentManager
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
            PRECIPITATION_ITEM_VIEW -> {
                binding = PrecipitationMainScreenItemBinding.inflate(inflater,parent,false)
            }
            AIR_QUALITY_VIEW -> {
                binding = AirQualityMainScreenItemBinding.inflate(inflater, parent, false)
            }
            VISIBILITY_VIEW -> {
                binding = VisibilityMainScreenItemBinding.inflate(inflater,parent, false)
            }
            UV_INDEX_VIEW -> {
                binding = UvIndexMainScreenItemBinding.inflate(inflater, parent, false)
            }
        }
        return MainScreenItemViewHolder(binding!!)
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = menuItemList[position]
        return when(currentItem.title){
            "Weather Cards" -> WEATHER_CARDS_VIEW
            "Quick Menu" -> QUICK_MENU_VIEW
            "Precipitation" -> PRECIPITATION_ITEM_VIEW
            "Air Quality" -> AIR_QUALITY_VIEW
            "Visibility" -> VISIBILITY_VIEW
            "UV Index" -> UV_INDEX_VIEW
            else -> TMP_MENU_ITEM_VIEW
        }
    }

    override fun onBindViewHolder(holder: MainScreenItemViewHolder, position: Int) {
        val item = menuItemList[position]
        val itemViewType = getItemViewType(position)
        holder.onBind(item, itemViewType, fragmentManager)
    }

    fun updateData(newMenuItemList: List<MainScreenMenuItem>){
        menuItemList = newMenuItemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = menuItemList.size

    companion object {
        const val WEATHER_CARDS_VIEW = 0
        const val QUICK_MENU_VIEW = 1
        const val TMP_MENU_ITEM_VIEW = 2
        const val PRECIPITATION_ITEM_VIEW = 3
        const val AIR_QUALITY_VIEW = 4
        const val VISIBILITY_VIEW = 5
        const val UV_INDEX_VIEW = 6
    }
}