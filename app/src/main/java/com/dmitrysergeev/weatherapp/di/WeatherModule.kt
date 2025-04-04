package com.dmitrysergeev.weatherapp.di

import com.dmitrysergeev.weatherapp.data.weather.WeatherRepository
import com.dmitrysergeev.weatherapp.data.weather.api.WeatherRepositoryApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryApiImpl: WeatherRepositoryApiImpl
    ): WeatherRepository

}