package com.dmitrysergeev.weatherapp.presentation.mainscreen.weathercardrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitrysergeev.weatherapp.R
import com.dmitrysergeev.weatherapp.data.weather.model.ForecastWeatherResponse
import com.dmitrysergeev.weatherapp.databinding.WeatherCardItemBinding

class WeatherCardViewHolder(
    private val binding: WeatherCardItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(forecastWeatherResponse: ForecastWeatherResponse){
        binding.apply {
            rainChance.text = itemView.context.getString(R.string.chance_of_rain, forecastWeatherResponse.forecast.forecastDay[0].dayApi.dailyChanceOfRain)
            weatherStatus.text = forecastWeatherResponse.currentWeather.condition.text
            locationText.text = itemView.context.getString(R.string.location, forecastWeatherResponse.location.name, forecastWeatherResponse.location.country)
            precipitationText.text = itemView.context.getString(R.string.no_precipitation_for_at_least,120)
            currentWeatherDegree.text = "${forecastWeatherResponse.currentWeather.tempC.toInt()}"
            maxDegreeText.text = itemView.context.getString(R.string.values_celsius, forecastWeatherResponse.forecast.forecastDay[0].dayApi.maxTempC.toInt())
            minDegreeText.text = itemView.context.getString(R.string.values_celsius, forecastWeatherResponse.forecast.forecastDay[0].dayApi.minTempC.toInt())
            feelsLikeText.text = itemView.context.getString(R.string.feels_like, forecastWeatherResponse.currentWeather.feelsLikeC.toInt())
            cardView.visibility = View.VISIBLE
        }
    }
}

class WeatherCardListAdapter(
    private var cards: List<ForecastWeatherResponse>
): RecyclerView.Adapter<WeatherCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherCardItemBinding.inflate(inflater,parent,false)
        return WeatherCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherCardViewHolder, position: Int) {
        val card: ForecastWeatherResponse = cards[position]
        holder.bind(card)
    }

    fun updateData(newCards: List<ForecastWeatherResponse>){
        cards = newCards
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cards.size
}