package com.example.jetweatherapp.repository

import android.util.Log
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.network.WeatherApi
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val api: WeatherApi){
    suspend fun getWeather(cityQuery: String, units: String) : DataOrException<Weather , Boolean , Exception>{
            val response = try {
                Log.d("asd", "getWeather: $units")
                api.getWeather(cityQuery , units = units)
            }catch (e :Exception){return DataOrException(e=e)
            }
        Log.d("INSIDE", "getWeather: $response ")
        return DataOrException(data = response)
    }
}