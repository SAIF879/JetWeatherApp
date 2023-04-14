package com.example.jetweatherapp.network

import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.model.WeatherItem
import com.example.jetweatherapp.model.WeatherObject
import com.example.jetweatherapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


// we want it to be singleton so as it doesn't create instances of itself
@Singleton
interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid : String = API_KEY
    ): Weather
}