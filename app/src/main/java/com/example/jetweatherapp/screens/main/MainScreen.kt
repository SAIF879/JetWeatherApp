package com.example.jetweatherapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
showData(mainViewModel = mainViewModel)
}

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun showData(mainViewModel: MainViewModel)
{
    val weatherData = produceState<DataOrException<Weather , Boolean ,Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
value = mainViewModel.getWeatherData("Ranchi")
    }.value

    if (weatherData.loading==true){
        CircularProgressIndicator()}

    else if(weatherData.data!=null){
        Text(text = "${weatherData.data}")
    }
}