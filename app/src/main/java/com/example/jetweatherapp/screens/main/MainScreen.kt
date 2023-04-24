package com.example.jetweatherapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

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
import coil.compose.rememberImagePainter
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDecimals
import com.example.jetweatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){

    val weatherData = produceState<DataOrException<Weather , Boolean ,Exception>>(
        initialValue = DataOrException(loading = true  )
    ){
        value = mainViewModel.getWeatherData("Ranchi")
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
    
  Scaffold(topBar = { WeatherAppBar(title =  weather.city.name + " ,  ${weather.city.country}", icon = Icons.Default.Search,navController = navController , elevation = 5.dp)}) {
      MainContent(data = weather)
  }

}

@Composable
fun MainContent(data: Weather) {
   val  imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    , modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = formatDate(data.list[0].dt),
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
                Text(text = formatDecimals(data.list[0].temp.day) +"°"+"f", style = MaterialTheme.typography.h4 , fontWeight = FontWeight.Bold)
                Text(text = data.list[0].weather[0].main , fontStyle = FontStyle.Italic )
                
            }
            
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter( imageUrl), contentDescription ="icon_image"  , modifier = Modifier.size(80.dp))
}
