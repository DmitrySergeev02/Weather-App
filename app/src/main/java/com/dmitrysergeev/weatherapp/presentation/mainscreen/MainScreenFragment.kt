package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.round

@AndroidEntryPoint
class MainScreenFragment: Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()

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

        binding.testText.setOnClickListener {
            binding.testText.text = "Oops, Changed"
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecast.collect {
                    binding.card.apply {
                        if (it==null){
                            rainChance.text = getString(R.string.chance_of_rain,60)
                            weatherStatus.text = "Partly Cloudy"
                            locationText.text = "Washington DC, USA"
                            precipitationText.text = getString(R.string.no_precipitation_for_at_least,120)
                            currentWeatherDegree.text = "26"
                            maxDegreeText.text = getString(R.string.values_celsius, 34)
                            minDegreeText.text = getString(R.string.values_celsius, 20)
                            feelsLikeText.text = getString(R.string.feels_like, 20)
                        } else {
                            rainChance.text = getString(R.string.chance_of_rain, it.forecast.forecastDay[0].dayApi.dailyChanceOfRain)
                            weatherStatus.text = "Partly Cloudy"
                            locationText.text = "${it.location.name} ${it.location.country}"
                            precipitationText.text = getString(R.string.no_precipitation_for_at_least,120)
                            currentWeatherDegree.text = "${it.currentWeather.tempC.toInt()}"
                            maxDegreeText.text = getString(R.string.values_celsius, it.forecast.forecastDay[0].dayApi.maxTempC.toInt())
                            minDegreeText.text = getString(R.string.values_celsius, it.forecast.forecastDay[0].dayApi.minTempC.toInt())
                            feelsLikeText.text = getString(R.string.feels_like, it.currentWeather.feelsLikeC.toInt())
                        }
                    }
                }
            }
        }

        viewModel.test()
    }

}