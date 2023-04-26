package com.example.jetweatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetweatherapp.data.WeatherDao
import com.example.jetweatherapp.data.WeatherDatabase
import com.example.jetweatherapp.network.WeatherApi
import com.example.jetweatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase) : WeatherDao = weatherDatabase.WeatherDAo()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database").fallbackToDestructiveMigration()
            .build()
    @Provides
    @Singleton
// we use convertor factory as the Retrofit requires to convert the RAW JSON file to objects :: )
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }
}

// we will not call this class on our own , but dagger hilt will call it