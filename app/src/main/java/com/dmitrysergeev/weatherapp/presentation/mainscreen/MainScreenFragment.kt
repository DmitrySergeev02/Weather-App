package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.FragmentMainScreenBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.quickmenu.QuickMenuItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MainScreenItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MainScreenItemListAdapter
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment: Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()

    private val cities: List<String> = listOf("Saint-Petersburg", "Moscow", "Tver")
    private val menuItems: List<QuickMenuItem> = listOf(
        QuickMenuItem(
            title = "UV Index",
            iconId = R.drawable.sunny,
            doOnClick = {
                Log.d("TAAAG", "UV Index")
            }
        ),
        QuickMenuItem(
            title = "Air Quality",
            iconId = R.drawable.air_quality,
            doOnClick = {
                Log.d("TAAAG", "Air Quality")
            }
        ),
        QuickMenuItem(
            title = "Visibility",
            iconId = R.drawable.humidity,
            doOnClick = {
                Log.d("TAAAG", "Visibility")
            }
        ),
        QuickMenuItem(
            title = "Precipitation",
            iconId = R.drawable.heavy_rain,
            doOnClick = {
                Log.d("TAAAG", "Precipitation")
            }
        ),
        QuickMenuItem(
            title = "Hurricane",
            iconId = R.drawable.hurricane,
            doOnClick = {
                Log.d("TAAAG", "Hurricane")
            }
        ),
        QuickMenuItem(
            title = "Visibility",
            iconId = R.drawable.eye_alt,
            doOnClick = {
                Log.d("TAAAG", "Visibility")
            }
        ),
        QuickMenuItem(
            title = "Wind",
            iconId = R.drawable.wind,
            doOnClick = {
                Log.d("TAAAG", "Wind")
            }
        ),
        QuickMenuItem(
            title = "Radar",
            iconId = R.drawable.radar,
            doOnClick = {
                Log.d("TAAAG", "Radar")
            }
        ),
        QuickMenuItem(
            title = "Earthquake",
            iconId = R.drawable.earthquake,
            doOnClick = {
                Log.d("TAAAG", "Earthquake")
            }
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createMainScreenItems(weatherResponsesList: List<ForecastWeatherResponse> = emptyList()): List<MainScreenItem>{
        val mainScreenItems: List<MainScreenItem> = listOf(
            MainScreenItem(
                title = "Weather Cards",
                data = weatherResponsesList
            ),
            MainScreenItem(
                title = "Quick Menu",
                data = menuItems
            ),
            MainScreenItem(
                title = "Forecast",
                data = "asda"
            ),
            MainScreenItem(
                title = "Precipitation",
                data = "Any"
            )
        )
        return mainScreenItems
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainScreenItems = createMainScreenItems()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MainScreenItemListAdapter(mainScreenItems)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MarginItemDecoration(requireContext(), marginHorizontalDp = 20, marginVerticalDp = 10))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecast.collect {
                    val newMainScreenItems = createMainScreenItems(it)
                    adapter.updateData(newMainScreenItems)
                }
            }
        }

        cities.forEach { city ->
            viewModel.getCityForecast(city)
        }
    }

}