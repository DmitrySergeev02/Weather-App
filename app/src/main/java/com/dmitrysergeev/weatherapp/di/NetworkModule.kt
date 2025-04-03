package com.dmitrysergeev.weatherapp.di

import com.dmitrysergeev.weatherapp.data.weather.api.WeatherApi
import com.dmitrysergeev.weatherapp.data.weather.api.WeatherInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(weatherInterceptor: WeatherInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(weatherInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): MoshiConverterFactory{
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: MoshiConverterFactory): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }

}