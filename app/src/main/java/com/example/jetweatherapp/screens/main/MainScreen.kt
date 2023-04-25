package com.example.jetweatherapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.navigation.WeatherScreens
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDecimals
import com.example.jetweatherapp.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
){

    val weatherData = produceState<DataOrException<Weather , Boolean ,Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
        value = mainViewModel.getWeatherData(city=city.toString())
    }.value

    if (weatherData.loading==true){
        CircularProgressIndicator()}


    else if(weatherData.data!=null){
//
        MainScaffold(weather = weatherData.data!! , navController = navController)
    }
}

@SuppressLint("ProduceStateDoesNotAssignValue", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather , navController: NavController)
{

    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + " ,  ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {navController.navigate(WeatherScreens.SearchScreen.name)},
            elevation = 5.dp
        )
    }) {
        MainContent(weatherItem = weather)
    }

}

@Composable
fun MainContent(weatherItem: Weather) {
   val  imageUrl = "https://openweathermap.org/img/wn/${weatherItem.list[0].weather[0].icon}.png"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    , modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = formatDate(weatherItem.list[0].dt),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp), shape = CircleShape , color = Color(0xffffc400)
        ) {
            Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
                //image
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.list[0].temp.day) +"°"+"f", style = MaterialTheme.typography.h4 , fontWeight = FontWeight.Bold)
                Text(text = weatherItem.list[0].weather[0].main , fontStyle = FontStyle.Italic )
                
            }
            
        }
        HumidityWindPressureRow(weather = weatherItem)
        Divider()
        SunSetSunRiseRow(weather = weatherItem)
        Text(text = "This Week ")
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), color = Color(0xffEEF1EF), shape = RoundedCornerShape(14.dp)) {
            LazyColumn(modifier = Modifier.padding(2.dp) , contentPadding = PaddingValues(1.dp)){
                items(weatherItem.list){
                    WeatherDetailRow(weather = it )
                    
                }
            }
        }
    }


}

