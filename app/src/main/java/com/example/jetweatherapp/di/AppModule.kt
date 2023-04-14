package com.example.jetweatherapp.di

import com.example.jetweatherapp.network.WeatherApi
import com.example.jetweatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
// we use convertor factory as the Retrofit requires to convert the RAW JSON file to objects :: )
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }
}

// we will not call this class on our own , but dagger hilt will call it