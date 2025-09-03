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
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.FragmentMainScreenBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MainScreenMenuItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MainScreenItemListAdapter
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment: Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()

    private val cities: List<String> = listOf("Saint-Petersburg", "Moscow", "Tver")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createMainScreenItems(weatherResponsesList: List<ForecastWeatherResponse> = emptyList(), layoutManager: LinearLayoutManager): List<MainScreenMenuItem>{
        val mainScreenMenuItems: ArrayList<MainScreenMenuItem> = arrayListOf(
            MainScreenMenuItem(
                title = "Weather Cards",
                data = weatherResponsesList,
                iconId = 0,
                doOnClick = {}
            ),
            MainScreenMenuItem(
                title = "UV Index",
                data = weatherResponsesList,
                iconId = R.drawable.sunny,
                doOnClick = {
                    layoutManager.scrollToPosition(2)
                    Log.d("TAAAG", "UV Index")
                }
            ),
            MainScreenMenuItem(
                title = "Air Quality",
                data = weatherResponsesList,
                iconId = R.drawable.air_quality,
                doOnClick = {
                    layoutManager.scrollToPosition(3+1)
                    Log.d("TAAAG", "Air Quality")
                }
            ),
            MainScreenMenuItem(
                title = "Another Item",
                data = "",
                iconId = R.drawable.humidity,
                doOnClick = {
                    layoutManager.scrollToPosition(4+1)
                    Log.d("TAAAG", "Visibility")
                }
            ),
            MainScreenMenuItem(
                title = "Precipitation",
                data = weatherResponsesList,
                iconId = R.drawable.heavy_rain,
                doOnClick = {
                    layoutManager.scrollToPosition(5+1)
                    Log.d("TAAAG", "Precipitation")
                }
            ),
            MainScreenMenuItem(
                title = "Hurricane",
                data = "",
                iconId = R.drawable.hurricane,
                doOnClick = {
                    layoutManager.scrollToPosition(6+1)
                    Log.d("TAAAG", "Hurricane")
                }
            ),
            MainScreenMenuItem(
                title = "Visibility",
                data = weatherResponsesList,
                iconId = R.drawable.eye_alt,
                doOnClick = {
                    layoutManager.scrollToPosition(7+1)
                    Log.d("TAAAG", "Visibility")
                }
            ),
            MainScreenMenuItem(
                title = "Wind",
                data = "",
                iconId = R.drawable.wind,
                doOnClick = {
                    layoutManager.scrollToPosition(8+1)
                    Log.d("TAAAG", "Wind")
                }
            ),
            MainScreenMenuItem(
                title = "Radar",
                data = "",
                iconId = R.drawable.radar,
                doOnClick = {
                    layoutManager.scrollToPosition(9+1)
                    Log.d("TAAAG", "Radar")
                }
            ),
            MainScreenMenuItem(
                title = "Earthquake",
                data = "",
                iconId = R.drawable.earthquake,
                doOnClick = {
                    layoutManager.scrollToPosition(10)
                    Log.d("TAAAG", "Earthquake")
                }
            )
        )
        mainScreenMenuItems.add(1, MainScreenMenuItem(
            title = "Quick Menu",
            data = mainScreenMenuItems,
            iconId = 0,
            doOnClick = {

            }
        ))
        return mainScreenMenuItems.toList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val mainScreenMenuItems = createMainScreenItems(layoutManager = layoutManager)
        val adapter = MainScreenItemListAdapter(mainScreenMenuItems, parentFragmentManager)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MarginItemDecoration(requireContext(), marginHorizontalDp = 20, marginVerticalDp = 10))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecast.collect {
                    val newMainScreenItems = createMainScreenItems(it, layoutManager)
                    adapter.updateData(newMainScreenItems)
                }
            }
        }

        cities.forEach { city ->
            viewModel.getCityForecast(city)
        }
    }

}