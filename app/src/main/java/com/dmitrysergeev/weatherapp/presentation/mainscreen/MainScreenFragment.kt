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
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.databinding.FragmentMainScreenBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.menu.QuickMenuItem
import com.dmitrysergeev.weatherapp.presentation.mainscreen.weathercardrecyclerview.WeatherCardListAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.cardsRecyclerView)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.cardsRecyclerView.layoutManager = layoutManager
        val adapter = WeatherCardListAdapter(listOf())
        binding.cardsRecyclerView.adapter = adapter

        binding.cardsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val listSize = adapter.listSize()
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisiblePosition > listSize && firstVisiblePosition%listSize == 0) {
                    recyclerView.scrollToPosition(listSize)
                } else if (firstVisiblePosition == listSize - 1){
                    recyclerView.scrollToPosition(listSize*2)
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecast.collect {
                    adapter.updateData(it)
                }
            }
        }

        cities.forEach { city ->
            viewModel.getCityForecast(city)
        }
    }

}