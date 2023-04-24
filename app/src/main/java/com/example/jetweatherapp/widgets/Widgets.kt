package com.example.jetweatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.model.WeatherItem
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDateTime
import com.example.jetweatherapp.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    //weatherItem.list[0].weather[0].icon
    val  imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        Modifier
            .padding(4.dp)
            .fillMaxWidth() , shape = CircleShape.copy(topEnd = CornerSize(6.dp)) , color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(start = 5.dp))
            WeatherStateImage(imageUrl = imageUrl)
            Surface(Modifier.padding(0.dp), shape = CircleShape , color = Color(0xffffc400)) {
                Text(
                    text = weather.weather[0].description,
                    Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Row(modifier = Modifier.padding(4.dp)) {
                Text(text = formatDecimals(weather.temp.max) +"°" , color =  Color.Red.copy(0.7f))
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = formatDecimals(weather.temp.min) +"°" , color =  Color.Gray.copy(0.7f))

            }

        }
    }
}

@Composable
fun SunSetSunRiseRow(weather: Weather) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            //Icon
            //sunrise
            Text(text = formatDateTime(weather.list[0].sunrise) , style = MaterialTheme.typography.caption )
        }
        Row {
            //Icon
            //sunset
            Text(text = formatDateTime(weather.list[0].sunset) , style = MaterialTheme.typography.caption)


        }

    }
}

@Composable
fun HumidityWindPressureRow(weather: Weather) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.padding(4.dp)) {
            //icon
            Text(text = "${weather.list[0].humidity} %"  , style = MaterialTheme.typography.caption)
        }
        Row {
            //icon
            Text(text = "${weather.list[0].pressure} psi"  , style = MaterialTheme.typography.caption)

        }
        Row {
            //icon
            Text(text = "${weather.list[0].humidity} mph"  , style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter( imageUrl), contentDescription ="icon_image"  , modifier = Modifier.size(80.dp))
}
