package com.example.jetweatherapp.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetweatherapp.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(topBar = {}) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement =  Arrangement.Center) {
                Text(text = stringResource(id = R.string.about_name) , style = MaterialTheme.typography.subtitle1 , fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = stringResource(id = R.string.api_used) , style = MaterialTheme.typography.subtitle1 , fontWeight = FontWeight.Bold)
            }
        }
    }

}


//@Composable
//fun AboutScreen(navController: NavController) {
//    Scaffold(topBar = {
//        WeatherAppBar(
//            title = "About",
//            icon = Icons.Default.ArrowBack,
//            false,
//            navController = navController){
//            navController.popBackStack()
//        }
//    }) {
//        Surface(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally,
//                  verticalArrangement = Arrangement.Center) {
//                Text(text = stringResource(id = R.string.about_app),
//                    style = MaterialTheme.typography.subtitle1,
//                    fontWeight = FontWeight.Bold)
//
//                Text(text = stringResource(id = R.string.api_used),
//                    style = MaterialTheme.typography.subtitle1,
//                    fontWeight = FontWeight.Light)
//
//            }
//
//        }
//
//    }
//
//}
