package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.databinding.FragmentMainScreenBinding
import com.dmitrysergeev.weatherapp.databinding.WeatherCardItemBinding
import dagger.hilt.android.AndroidEntryPoint

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

        binding.card.apply {
            rainChance.text = getString(R.string.chance_of_rain,60)
            weatherStatus.text = "Partly Clody"
            locationText.text = "Washington DC, USA"
            precipitationText.text = getString(R.string.no_precipitation_for_at_least,120)
            currentWeatherDegree.text = "26"
            maxDegreeText.text = getString(R.string.values_celsius, 34)
            minDegreeText.text = getString(R.string.values_celsius, 20)
            feelsLikeText.text = getString(R.string.feels_like, 20)
        }

        viewModel.test()
    }

}