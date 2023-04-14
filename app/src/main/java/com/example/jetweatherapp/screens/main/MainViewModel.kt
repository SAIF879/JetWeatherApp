package com.example.jetweatherapp.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.model.WeatherObject
import com.example.jetweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

 suspend fun getWeatherData(city : String) : DataOrException<Weather , Boolean , Exception>{
     return repository.getWeather(city)
 }
}




