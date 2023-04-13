package com.example.jetweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    // this class ties everything ie in terms of dependencyInjection

}