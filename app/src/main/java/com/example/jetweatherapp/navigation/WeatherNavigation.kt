package com.example.jetweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherapp.screens.about.AboutScreen
import com.example.jetweatherapp.screens.favourite.FavouriteScreen
import com.example.jetweatherapp.screens.main.MainScreen
import com.example.jetweatherapp.screens.main.MainViewModel
import com.example.jetweatherapp.screens.search.SearchScreen
import com.example.jetweatherapp.screens.settings.SettingsScreen
import com.example.jetweatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){

        composable(WeatherScreens.SplashScreen.name){ WeatherSplashScreen(navController=navController) }

        val  route = WeatherScreens.MainScreen.name 
        composable("$route/{city}" , arguments = listOf(navArgument(name = "city"){type = NavType.StringType})){navBack->
            navBack.arguments?.getString("city").let{city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
            
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController) }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.FavouriteScreen.name){
            FavouriteScreen(navController = navController)
             }

        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
            }


    }
}